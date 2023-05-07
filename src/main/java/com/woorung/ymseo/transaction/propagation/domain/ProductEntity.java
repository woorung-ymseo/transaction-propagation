package com.woorung.ymseo.transaction.propagation.domain;

import com.woorung.ymseo.transaction.propagation.infrastructure.repository.ProductRepository;
import com.woorung.ymseo.transaction.propagation.service.dto.req.CreateProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Table(name = "Product")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ItemEntity> items;

    public final static ProductEntity create(ProductRepository productRepository, CreateProductDto createProduct) {
        final var product = ProductEntity.builder()
                .name(createProduct.getName())
                .price(createProduct.getPrice())
                .build();

        if (!ObjectUtils.isEmpty(createProduct)) {
            final var items = createProduct.getItems().stream()
                    .map(i -> ItemEntity.builder()
                            .name(i.getName())
                            .additionalPrice(i.getAdditionalPrice())
                            .build())
                    .collect(Collectors.toList());

            product.changeItems(items);
        }

        productRepository.save(product);

        return product;
    }

    public void changeItems(List<ItemEntity> items) {
        this.items = items;
    }
}
