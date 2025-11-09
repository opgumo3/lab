package com.example.ddd.demo.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @EmbeddedId
    private OrderId id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "memberEmail", column = @Column(name = "orderer_email")),
            @AttributeOverride(name = "memberName", column = @Column(name = "orderer_name"))
    })
    private Orderer orderer;

    @ElementCollection(cascade = CascadeType.ALL)
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderLine> orderLines = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "receiverName", column = @Column(name = "receiver_name")),
            @AttributeOverride(name = "receiverPhone", column = @Column(name = "receiver_phone")),
            @AttributeOverride(name = "shippingAddress1", column = @Column(name = "shipping_addr1")),
            @AttributeOverride(name = "shippingAddress2", column = @Column(name = "shipping_addr2")),
            @AttributeOverride(name = "shippingZipcode", column = @Column(name = "shipping_zipcode"))
    })
    private ShippingInfo shippingInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OrderState state;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        validateOrder(orderer, orderLines, shippingInfo);

        this.id = OrderId.generate();
        this.orderer = orderer;
        this.orderLines = new ArrayList<>(orderLines);
        this.shippingInfo = shippingInfo;
        this.state = OrderState.PAYMENT_WAITING;
        this.orderDate = LocalDateTime.now();
    }

    private void validateOrder(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        if (orderer == null) {
            throw new IllegalArgumentException("주문자 정보는 필수입니다.");
        }
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("주문 상품은 최소 1개 이상이어야 합니다.");
        }
        if (shippingInfo == null) {
            throw new IllegalArgumentException("배송지 정보는 필수입니다.");
        }
    }

    public BigDecimal getTotalAmounts() {
        return orderLines.stream()
                .map(OrderLine::getAmounts)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        if (newShippingInfo == null) {
            throw new IllegalArgumentException("배송지 정보는 필수입니다.");
        }
        this.shippingInfo = newShippingInfo;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if (!state.isShippingChangeable()) {
            throw new IllegalStateException("이미 배송이 시작된 주문은 변경할 수 없습니다.");
        }
    }

    public void startShipping() {
        verifyState(OrderState.PAYMENT_WAITING);
        this.state = OrderState.SHIPPED;
    }

    public void completeDelivery() {
        verifyState(OrderState.DELIVERING);
        this.state = OrderState.DELIVERY_COMPLETED;
    }

    private void verifyState(OrderState expectedState) {
        if (this.state != expectedState) {
            throw new IllegalStateException(
                String.format("현재 상태(%s)에서는 해당 작업을 수행할 수 없습니다.", state.getDescription()));
        }
    }
}