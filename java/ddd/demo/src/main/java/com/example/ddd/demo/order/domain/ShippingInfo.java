package com.example.ddd.demo.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfo {
    private String receiverName;
    private String receiverPhone;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;

    public ShippingInfo(String receiverName, String receiverPhone,
                       String shippingAddress1, String shippingAddress2,
                       String shippingZipcode) {
        if (receiverName == null || receiverName.trim().isEmpty()) {
            throw new IllegalArgumentException("받는 사람 이름은 필수입니다.");
        }
        if (receiverPhone == null || receiverPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("받는 사람 전화번호는 필수입니다.");
        }
        if (shippingAddress1 == null || shippingAddress1.trim().isEmpty()) {
            throw new IllegalArgumentException("배송 주소는 필수입니다.");
        }
        if (shippingZipcode == null || shippingZipcode.trim().isEmpty()) {
            throw new IllegalArgumentException("우편번호는 필수입니다.");
        }

        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.shippingZipcode = shippingZipcode;
    }

    public String getFullAddress() {
        return String.format("(%s) %s %s", shippingZipcode, shippingAddress1,
                           shippingAddress2 != null ? shippingAddress2 : "").trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInfo that = (ShippingInfo) o;
        return Objects.equals(receiverName, that.receiverName) &&
               Objects.equals(receiverPhone, that.receiverPhone) &&
               Objects.equals(shippingAddress1, that.shippingAddress1) &&
               Objects.equals(shippingAddress2, that.shippingAddress2) &&
               Objects.equals(shippingZipcode, that.shippingZipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverName, receiverPhone, shippingAddress1,
                          shippingAddress2, shippingZipcode);
    }
}