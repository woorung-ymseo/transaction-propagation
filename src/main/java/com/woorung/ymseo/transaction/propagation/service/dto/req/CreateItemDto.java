package com.woorung.ymseo.transaction.propagation.service.dto.req;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateItemDto {

    private String name;
    private int additionalPrice;
}
