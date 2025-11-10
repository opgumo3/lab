package com.example.ddd.demo.order.application;

import com.example.ddd.demo.order.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderId placeOrder(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        Order order = new Order(orderer, orderLines, shippingInfo);
        orderRepository.save(order);
        return order.getId();
    }

    public void changeShippingInfo(OrderId orderId, ShippingInfo newShippingInfo) {
        Order order = findOrderById(orderId);
        order.changeShippingInfo(newShippingInfo);
    }

    public void cancelOrder(OrderId orderId) {
        Order order = findOrderById(orderId);
        order.cancel();
    }

    public void startShipping(OrderId orderId) {
        Order order = findOrderById(orderId);
        order.startShipping();
    }

    public void completeDelivery(OrderId orderId) {
        Order order = findOrderById(orderId);
        order.completeDelivery();
    }

    @Transactional(readOnly = true)
    public Order findOrderById(OrderId orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다: " + orderId));
    }

    @Transactional(readOnly = true)
    public List<Order> findOrdersByOrderer(Orderer orderer) {
        return orderRepository.findByOrderer(orderer);
    }

    @Transactional(readOnly = true)
    public List<Order> findOrdersByState(OrderState state) {
        return orderRepository.findByState(state);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}