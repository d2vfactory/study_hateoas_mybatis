package com.example.hateoasmybatis.model.dto;


import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDate;


@Data
@Alias("item")
public class Item {

    private int prodId;

    private String prodName;

    private int price;

    private int itemId;

    private int itemType;

    private String cmsItemId;

    private LocalDate regDate;

}
