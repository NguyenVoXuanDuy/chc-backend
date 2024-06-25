package com.xuanduy.chc_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Valid
    private Set<CartDetail> cartDetails;

    @OneToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private User user;

    public void addCartDetail(CartDetail cartDetail) {
        System.out.println();
        for (CartDetail cartDetail1 : cartDetails) {
            if (cartDetail1.getProduct().getId().equals(cartDetail.getProduct().getId())) {
                cartDetails.remove(cartDetail1);
                break;
            }
        }
        cartDetails.add(cartDetail);
    }
}
