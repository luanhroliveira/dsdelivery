package com.dev.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.dsdelivery.dto.OrderDTO;
import com.dev.dsdelivery.dto.ProductDTO;
import com.dev.dsdelivery.entities.Order;
import com.dev.dsdelivery.entities.OrderStatus;
import com.dev.dsdelivery.entities.Product;
import com.dev.dsdelivery.repositories.OrderRepository;
import com.dev.dsdelivery.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
				OrderStatus.PENDING);

		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);

		return new OrderDTO(order);
	}

	@Transactional(readOnly = true)
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);

		return new OrderDTO(order);
	}
}
