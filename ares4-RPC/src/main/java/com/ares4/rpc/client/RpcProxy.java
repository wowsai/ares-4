package com.ares4.rpc.client;

import com.ares4.rpc.common.ServiceDiscovery;
import com.ares4.rpc.model.RpcRequest;
import com.ares4.rpc.model.RpcResponse;
import com.google.common.net.HostAndPort;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.UUID;

public class RpcProxy {
    private ServiceDiscovery serviceDiscovery;
    private String serverAddress;

    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[] { interfaceClass },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                        RpcRequest request = new RpcRequest();
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);
                        if(serviceDiscovery != null) {
                            serverAddress = serviceDiscovery.discover();
                        }

                        HostAndPort hostAndPort = HostAndPort.fromString(serverAddress);
                        RpcClient client = new RpcClient(hostAndPort.getHostText(), hostAndPort.getPort());
                        RpcResponse response = client.send(request);
                        if(response.getError() != null) throw response.getError();
                        return response.getResult();
                    }
                }
        );
    }
}
