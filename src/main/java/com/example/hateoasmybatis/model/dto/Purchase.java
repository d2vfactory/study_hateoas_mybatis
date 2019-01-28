package com.example.hateoasmybatis.model.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

@Data
@Alias("purchase")
public class Purchase  {

    private String chargeNo;
    private String userNo;
    private String userId;
    private String userName;
    private String prodName;
    private int prodId;
    private int prodType;
    private int price;
    private int corpId;
    private String ymd;
    private LocalDate regDate;
    private int useState;

}
