package com.dev.dsdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.dsdelivery.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
