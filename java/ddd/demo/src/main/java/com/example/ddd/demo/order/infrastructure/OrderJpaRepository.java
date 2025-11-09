package com.example.ddd.demo.order.infrastructure;

import com.example.ddd.demo.order.domain.Order;
import com.example.ddd.demo.order.domain.OrderId;
import com.example.ddd.demo.order.domain.OrderState;
import com.example.ddd.demo.order.domain.Orderer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, OrderId> {

    @Query("SELECT o FROM Order o WHERE o.orderer.memberEmail = :#{#orderer.memberEmail} AND o.orderer.memberName = :#{#orderer.memberName}")
    List<Order> findByOrderer(@Param("orderer") Orderer orderer);

    List<Order> findByState(OrderState state);
}