package com.woorung.ymseo.transaction.propagation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Item")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(name = "additional_price")
    private int additionalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
}
