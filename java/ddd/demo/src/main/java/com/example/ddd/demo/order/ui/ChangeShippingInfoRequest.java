package com.example.ddd.demo.order.ui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeShippingInfoRequest {
    private String receiverName;
    private String receiverPhone;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
}