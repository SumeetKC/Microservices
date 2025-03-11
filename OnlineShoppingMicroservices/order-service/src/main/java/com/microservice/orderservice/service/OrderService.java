package com.microservice.orderservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.orderservice.dto.OrderLineItemsDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.model.OrderLineItems;
import com.microservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		List<OrderLineItems> orderLineItemList = orderRequest.getOrderLineItemsDtoList().stream()
				.map(this::mapToOrderLineItems).collect(Collectors.toList());

		order.setOrderNumber(UUID.randomUUID().toString());
		order.setOrderLineItems(orderLineItemList);

		orderRepository.save(order);
	}

	public OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {

		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setId(orderLineItemsDto.getId());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setPrice(orderLineItemsDto.getPrice());

		return orderLineItems;
	}

	public List<OrderResponse> getOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream().map(this::mapToOrderResponse).toList();
	}

	public OrderResponse mapToOrderResponse(Order order) {

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderNumber(order.getOrderNumber());
		orderResponse
				.setOrderLineItemsDtoList(order.getOrderLineItems().stream().map(this::mapToOrderLineItems).toList());
		return orderResponse;
	}

	public OrderLineItemsDto mapToOrderLineItems(OrderLineItems orderLineItems) {

		OrderLineItemsDto orderLineItemsDto = new OrderLineItemsDto();
		orderLineItemsDto.setId(orderLineItems.getId());
		orderLineItemsDto.setSkuCode(orderLineItems.getSkuCode());
		orderLineItemsDto.setQuantity(orderLineItems.getQuantity());
		orderLineItemsDto.setPrice(orderLineItems.getPrice());

		return orderLineItemsDto;
	}

}
