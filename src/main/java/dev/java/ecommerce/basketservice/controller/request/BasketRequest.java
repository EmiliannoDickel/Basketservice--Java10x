package dev.java.ecommerce.basketservice.controller.request;

import dev.java.ecommerce.basketservice.entity.Product;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
