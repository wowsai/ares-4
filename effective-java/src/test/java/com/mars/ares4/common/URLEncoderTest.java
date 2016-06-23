package com.mars.ares4.common;

import org.junit.Test;

import java.net.URLEncoder;

public class URLEncoderTest {
    @Test
    public void testURLEncode() throws Exception {
        String source = "工商银行(5220)";
        System.out.println(URLEncoder.encode(source, "utf-8"));
        System.out.println(URLEncoder.encode(source, "gbk"));
        System.out.println(URLEncoder.encode(source, "iso-8859-1"));
    }
}
