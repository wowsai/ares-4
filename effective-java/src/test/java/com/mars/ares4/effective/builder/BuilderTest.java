package com.mars.ares4.effective.builder;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import static com.mars.ares4.effective.builder.Transfer.DEFAULT;

public class BuilderTest {
    @Test
    public void testBuilder() {
        System.out.println(
                Transfer.builder()
                        .from("10000001")
                        .to("400000001")
                        .amount(BigDecimal.valueOf(1000.10))
                        .digest("客户调账")
                        .transferDateTime(new Date())
                        .build()
        );

        System.out.println(DEFAULT);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println(df.format(BigDecimal.ZERO));
        System.out.println(df.format(BigDecimal.ONE));
        System.out.println(df.format(BigDecimal.valueOf(1231.2363)));
    }
}
