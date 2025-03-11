package com.microservice.orderservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {

	private String orderNumber;
	private List<OrderLineItemsDto> orderLineItemsDtoList;

}
