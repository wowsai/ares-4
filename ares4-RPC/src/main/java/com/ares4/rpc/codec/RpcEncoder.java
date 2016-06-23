package com.ares4.rpc.codec;

import com.ares4.rpc.common.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> clazz;

    public RpcEncoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object t, ByteBuf byteBuf) throws Exception {
        byte[] data = SerializationUtil.serialize(t);
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }
}
