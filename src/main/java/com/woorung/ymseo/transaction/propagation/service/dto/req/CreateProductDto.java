package com.woorung.ymseo.transaction.propagation.service.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateProductDto {

    private String name;
    private int price;
    private List<CreateItemDto> items;
}
