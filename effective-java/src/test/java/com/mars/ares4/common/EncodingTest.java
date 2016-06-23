package com.mars.ares4.common;

import com.google.common.io.BaseEncoding;
import com.google.common.primitives.Longs;
import com.mars.ares4.encoding.Hashids;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

public class EncodingTest {
    public static final BaseEncoding BASE64_URL = BaseEncoding.base64Url().omitPadding();

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return BASE64_URL.encode(Longs.toByteArray(uuid.getMostSignificantBits() + uuid.getLeastSignificantBits()));
    }

    @Test
    public void testUUID() {
        for(int i = 0; i < 100; i++) {
            String uuid = uuid();
            System.out.printf("'%s' with length %d\n", uuid, uuid.length());
        }
    }

    @Test
    public void testHashIDs() {
//        UUID uuid = UUID.randomUUID();
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[8];
        secureRandom.nextBytes(salt);
        Hashids hashids = new Hashids(BASE64_URL.encode(salt), 8);
        // 2147483647
        String encoded = hashids.encrypt(0x7ffffffffL);
        System.out.println(encoded);
        System.out.println(Arrays.toString(hashids.decrypt(encoded)));
    }

    @Test
    public void advancedUUID() {
        Hashids hashids = new Hashids();
        System.out.println(hashids.encrypt(Long.MAX_VALUE));
        System.out.println(hashids.decrypt(hashids.encrypt(Long.MAX_VALUE))[0]);
    }
}
