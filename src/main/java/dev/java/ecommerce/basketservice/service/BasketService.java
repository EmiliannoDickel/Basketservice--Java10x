package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.entity.Product;
import dev.java.ecommerce.basketservice.entity.Status;
import dev.java.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket createBasket(BasketRequest basketRequest){

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new IllegalStateException("Cliente está com o carrinho aberto");
                });


        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductId(productRequest.productID());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });
        Basket basket = Basket.builder().products(products).client(basketRequest.clientId()).status(Status.OPEN).build();
        basket.CalculateTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket getBasketById(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Carrinho não encontrado"));
    }
}
