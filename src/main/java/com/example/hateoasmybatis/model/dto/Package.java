package com.example.hateoasmybatis.model.dto;


import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDate;


@Data
@Alias("package")
@Relation(collectionRelation = "packages")
public class Package {

    private int prodId;

    private String prodName;

    private int price;

    private int packageId;

    private int packageType;

    private LocalDate regDate;

}
