package com.example.ddd.demo.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orderer {
    private String memberEmail;
    private String memberName;

    public Orderer(String memberEmail, String memberName) {
        if (memberEmail == null || memberEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("주문자 이메일은 필수입니다.");
        }
        if (memberName == null || memberName.trim().isEmpty()) {
            throw new IllegalArgumentException("주문자 이름은 필수입니다.");
        }
        this.memberEmail = memberEmail;
        this.memberName = memberName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderer orderer = (Orderer) o;
        return Objects.equals(memberEmail, orderer.memberEmail) &&
               Objects.equals(memberName, orderer.memberName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberEmail, memberName);
    }
}