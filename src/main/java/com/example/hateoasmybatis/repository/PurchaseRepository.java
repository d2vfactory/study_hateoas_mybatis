package com.example.hateoasmybatis.repository;

import com.example.hateoasmybatis.model.dto.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PurchaseRepository {

    Purchase selectByChargeNo(String chargeNo);

    List<Purchase> selectAllByUserId(String userId);
}
