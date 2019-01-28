package com.example.hateoasmybatis.controller;

import com.example.hateoasmybatis.model.assembler.PackageAssembler;
import com.example.hateoasmybatis.model.dto.Package;
import com.example.hateoasmybatis.repository.PackageRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/rest/packages")
@RestController
public class PackageController {

    private final PackageRepository repository;

    private final PackageAssembler packageAssembler;

    public PackageController(PackageRepository repository, PackageAssembler packageAssembler) {
        this.repository = repository;
        this.packageAssembler = packageAssembler;
    }

    @GetMapping(produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAllProduct(Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber()+1, pageable.getPageSize());

        List<Package> packageAll = repository.selectAll();

        List<Resource> packageResources = packageAll.stream()
                .map(x -> packageAssembler.toResource(x))
                .collect(Collectors.toList());

        PageInfo<Package> pageInfo = new PageInfo<>(packageAll);

        PagedResources pagedResources = new PagedResources(
                packageResources,
                new PagedResources.PageMetadata(pageable.getPageSize(), pageable.getPageNumber(), pageInfo.getTotal())
        );


        return ResponseEntity.ok(pagedResources);

    }

    @GetMapping(value = "/{prodId}", produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<Resource<Package>> getProduct(@PathVariable int prodId) {
        // purchase to purchaseResource
        Package pkg = repository.selectByProdId(prodId);

        return ResponseEntity.ok(packageAssembler.toResource(pkg));

    }
}
