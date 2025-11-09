package com.example.ddd.demo.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(OrderId id);

    List<Order> findByOrderer(Orderer orderer);

    List<Order> findByState(OrderState state);

    void save(Order order);

    void delete(Order order);

    List<Order> findAll();
}