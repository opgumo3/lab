package com.example.ddd.demo.order.ui;

import com.example.ddd.demo.order.domain.OrderLine;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderRequest {
    private String ordererEmail;
    private String ordererName;
    private List<OrderLine> orderLines;
    private String receiverName;
    private String receiverPhone;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
}