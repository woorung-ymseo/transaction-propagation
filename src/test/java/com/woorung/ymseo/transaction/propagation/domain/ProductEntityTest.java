package com.woorung.ymseo.transaction.propagation.domain;

import com.woorung.ymseo.transaction.propagation.infrastructure.repository.ProductRepository;
import com.woorung.ymseo.transaction.propagation.service.dto.req.CreateItemDto;
import com.woorung.ymseo.transaction.propagation.service.dto.req.CreateProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ActiveProfiles(profiles = {"test"})
@ExtendWith(MockitoExtension.class)
class ProductEntityTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void create() {

        // given
        String productName = "테스트 상품";
        int productPrice = 1000;
        String itemName = "테스트 품목";
        int additionalPrice = 100;
        long productId = 1L;
        long itemId = 1L;

        final var createProduct = CreateProductDto.builder()
                .name(productName)
                .price(productPrice)
                .items(List.of(CreateItemDto.builder()
                                .name(itemName)
                                .additionalPrice(additionalPrice)
                        .build()))
                .build();

        final var items = new ArrayList<ItemEntity>();
        items.add(ItemEntity.builder()
                .name(itemName)
                .additionalPrice(additionalPrice)
                .build());

        final var product = ProductEntity.builder()
                .name(productName)
                .price(productPrice)
                .items(items)
                .build();

        final var resultItems = List.of(
                ItemEntity.builder()
                        .id(itemId)
                        .name(itemName)
                        .additionalPrice(additionalPrice)
                        .build()
        );

        final var resultProduct = ProductEntity.builder()
                .id(productId)
                .name(productName)
                .price(productPrice)
                .items(resultItems)
                .build();

        // when
        given(productRepository.save(any(ProductEntity.class))).willReturn(resultProduct);

        // then
        final var productEntity = ProductEntity.create(productRepository, createProduct);

        assertAll(
                () -> assertEquals(productEntity.getName(), productName),
                () -> assertEquals(productEntity.getPrice(), productPrice),
                () -> assertEquals(productEntity.getItems().size(), 1),
                () -> assertEquals(productEntity.getItems().get(0).getName(), itemName),
                () -> assertEquals(productEntity.getItems().get(0).getAdditionalPrice(), additionalPrice)
        );
    }
}