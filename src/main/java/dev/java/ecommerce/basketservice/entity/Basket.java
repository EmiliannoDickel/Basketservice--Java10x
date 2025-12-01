package dev.java.ecommerce.basketservice.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;



@Document(collation = "basket")// anotação para dizer que essa classe é um documento do mongoDB
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket {
    @Id
    private String id;
    private Long client;
    private BigDecimal price;
    private List<Product> products;
    private Status status;


