package com.example.hateoasmybatis.repository;

import com.example.hateoasmybatis.model.dto.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemRepository {
    Item selectByProdId(int prodId);

    List<Item> selectAll();
}
