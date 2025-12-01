package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products") // adicionamos isso para configurar o redis, assim quando chamarmos esse metodo ele vai salvar o resultado no cache, com o tempo configurado la no application.yml
    public List<PlatziProductResponse> getProducts(){
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key = "#id")
    public PlatziProductResponse getProductId(Long id) {
        return platziStoreClient.getProductById(id);
    }


}
