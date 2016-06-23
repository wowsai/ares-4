package com.ares4.rpc.codec;

import com.ares4.rpc.common.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {
    private Class<?> type;

    public RpcDecoder(Class<?> type) {
        this.type = type;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4) return;
        in.markReaderIndex();
        int dataLength = in.readInt();
        if(dataLength < 0) {
            ctx.close();
        }
        if(in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = SerializationUtil.deserialize(data, type);
        out.add(obj);
    }
}
