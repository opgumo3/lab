package com.example.ddd.demo.order.infrastructure;

import com.example.ddd.demo.order.domain.Order;
import com.example.ddd.demo.order.domain.OrderId;
import com.example.ddd.demo.order.domain.OrderRepository;
import com.example.ddd.demo.order.domain.OrderState;
import com.example.ddd.demo.order.domain.Orderer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public JpaOrderRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderJpaRepository.findById(id);
    }

    @Override
    public List<Order> findByOrderer(Orderer orderer) {
        return orderJpaRepository.findByOrderer(orderer);
    }

    @Override
    public List<Order> findByState(OrderState state) {
        return orderJpaRepository.findByState(state);
    }

    @Override
    public void save(Order order) {
        orderJpaRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderJpaRepository.delete(order);
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll();
    }
}