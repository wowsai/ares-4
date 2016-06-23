package com.ares4.rpc;

import com.ares4.rpc.annotations.RpcService;
import com.ares4.rpc.codec.RpcDecoder;
import com.ares4.rpc.codec.RpcEncoder;
import com.ares4.rpc.handler.RpcHandler;
import com.ares4.rpc.model.RpcRequest;
import com.ares4.rpc.model.RpcResponse;
import com.ares4.rpc.registry.ServiceRegistry;
import com.google.common.collect.Maps;
import com.google.common.net.HostAndPort;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class RpcServer implements ApplicationContextAware, InitializingBean {
    public static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private String serverAddress;
    private ServiceRegistry registry;

    private Map<String, Object> handlerMap = Maps.newHashMap();

    public RpcServer(String serverAddress, ServiceRegistry registry) {
        this.serverAddress = serverAddress;
        this.registry = registry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new RpcDecoder(RpcRequest.class))
                                    .addLast(new RpcEncoder(RpcResponse.class))
                                    .addLast(new RpcHandler(handlerMap));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            HostAndPort hostPort = HostAndPort.fromString(serverAddress);
            ChannelFuture future = bootstrap.bind(hostPort.getHostText(), hostPort.getPort()).sync();
            logger.debug("server started on port {}", hostPort.getPort());
            if(registry != null) {
                registry.register(serverAddress);
            }
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if(!serviceBeanMap.isEmpty()) {
            for(Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }
}
