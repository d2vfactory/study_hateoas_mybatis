package com.example.hateoasmybatis.repository;

import com.example.hateoasmybatis.model.dto.Package;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PackageRepository {
    Package selectByProdId(int prodId);

    List<Package> selectAll();
}
