package com.ares4.rpc.common;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

public class SerializationUtil {
    public static final Logger logger = LoggerFactory.getLogger(SerializationUtil.class);

    private static Objenesis objenesis = new ObjenesisStd(true);

    public static final LoadingCache<Class<?>, Schema<?>> cachedSchema = CacheBuilder.newBuilder()
            .build(new CacheLoader<Class<?>, Schema<?>>() {
                @Override
                public Schema<?> load(Class<?> key) throws Exception {
                    return RuntimeSchema.createFrom(key);
                }
            });

    private SerializationUtil() {}

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        try {
            return (Schema<T>)cachedSchema.get(clazz);
        } catch (ExecutionException e) {
            logger.error("get schema failed.", e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <T> byte[] serialize(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            T message = objenesis.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtobufIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
