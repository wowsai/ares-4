package com.mars.ares4.effective.builder;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.util.Date;

public class Transfer {
    public final String from;
    public final String to;
    public final BigDecimal amount;
    public final String digest;
    public final Date transferDateTime;

    public static final Transfer DEFAULT = Transfer.builder()
            .from("1000000")
            .to("4000000")
            .amount(BigDecimal.ZERO)
            .build();

    private Transfer(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.amount = builder.amount;
        this.digest = builder.digest;
        this.transferDateTime = builder.transferDateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Transfer.class)
                .omitNullValues()
                .add("from", from)
                .add("to", to)
                .add("amount", amount)
                .add("digest", digest)
                .add("transferDateTime", transferDateTime)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(from, to, amount, digest, transferDateTime);
    }

    public static class Builder implements com.mars.ares4.effective.builder.Builder<Transfer> {
        private String from;
        private String to;
        private BigDecimal amount;
        private String digest;
        private Date transferDateTime;

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder digest(String digest) {
            this.digest = digest;
            return this;
        }

        public Builder transferDateTime(Date transferDateTime) {
            this.transferDateTime = transferDateTime;
            return this;
        }

        public Transfer build() {
            return new Transfer(this);
        }
    }
}
