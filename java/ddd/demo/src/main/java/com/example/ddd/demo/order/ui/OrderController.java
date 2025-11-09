package com.example.ddd.demo.order.ui;

import com.example.ddd.demo.order.application.OrderService;
import com.example.ddd.demo.order.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrderRequest request) {
        Orderer orderer = new Orderer(request.getOrdererEmail(), request.getOrdererName());
        ShippingInfo shippingInfo = new ShippingInfo(
                request.getReceiverName(),
                request.getReceiverPhone(),
                request.getShippingAddress1(),
                request.getShippingAddress2(),
                request.getShippingZipcode()
        );

        OrderId orderId = orderService.placeOrder(orderer, request.getOrderLines(), shippingInfo);
        return ResponseEntity.ok(orderId.getValue());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.findOrderById(new OrderId(orderId));
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/shipping-info")
    public ResponseEntity<Void> changeShippingInfo(
            @PathVariable String orderId,
            @RequestBody ChangeShippingInfoRequest request) {
        ShippingInfo newShippingInfo = new ShippingInfo(
                request.getReceiverName(),
                request.getReceiverPhone(),
                request.getShippingAddress1(),
                request.getShippingAddress2(),
                request.getShippingZipcode()
        );

        orderService.changeShippingInfo(new OrderId(orderId), newShippingInfo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(new OrderId(orderId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/start-shipping")
    public ResponseEntity<Void> startShipping(@PathVariable String orderId) {
        orderService.startShipping(new OrderId(orderId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/complete-delivery")
    public ResponseEntity<Void> completeDelivery(@PathVariable String orderId) {
        orderService.completeDelivery(new OrderId(orderId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-state/{state}")
    public ResponseEntity<List<Order>> getOrdersByState(@PathVariable OrderState state) {
        List<Order> orders = orderService.findOrdersByState(state);
        return ResponseEntity.ok(orders);
    }
}