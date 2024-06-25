package com.xuanduy.chc_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int quantity;

    //price is necessary because the price of the product may change in the future
    private int price;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Product product;

    @ManyToOne
    @JoinColumn(name = "order_record_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    OrderRecord orderRecord;

}

