package edu.example.study.utill;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
/**
 * Created by Taxz on 2018/3/29.
 * 支持序列化和反序列化
 */
public class RedisObjectSerializer implements RedisSerializer {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o.equals(null)) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(o);
        }catch (Exception e ){
            return EMPTY_ARRAY;

        }

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes== null || bytes.length == 0) {
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }
}
