package com.microservice.orderservice.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;
import com.microservice.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	@TimeLimiter(name= "inventory")
	@Retry(name = "inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponse> getOrders() {
		return orderService.getOrders();
	}

	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
		return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after sometime");
	}

}
