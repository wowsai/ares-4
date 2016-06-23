package com.mars.ares4.sms;

import com.google.common.base.Charsets;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class KDSMSDemo {
    public static final Logger logger = LoggerFactory.getLogger(KDSMSDemo.class);

    public static final String SMS_API = "http://api.kingdee.com/msgsrv/sms";

    public static URI SMS_URI;

    static {
        try {
            SMS_URI = new URIBuilder(SMS_API)
                    .addParameter("client_id", "200121")
                    .addParameter("client_secret", "c95f5319d22f3d133a6d8840b496c33f")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String result = Request.Post(SMS_URI)
                .bodyForm(
                        Form.form()
                                .add("phone", "18665830320")
                                .add("smscode", "10095")
                                .add("country_code", "86")
                                .add("content", "156262")
                                .build())
                .execute()
                .returnContent()
                .asString(Charsets.UTF_8);
        System.out.println(result);
    }
}
