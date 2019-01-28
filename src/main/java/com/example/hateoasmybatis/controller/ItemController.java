package com.example.hateoasmybatis.controller;


import com.example.hateoasmybatis.model.assembler.ItemsAssembler;
import com.example.hateoasmybatis.model.dto.Item;
import com.example.hateoasmybatis.model.dto.Purchase;
import com.example.hateoasmybatis.model.resource.ItemResource;
import com.example.hateoasmybatis.repository.ItemRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/rest/items")
@RestController
public class ItemController {

    private final ItemRepository repository;

    private final ItemsAssembler itemsAssembler;

    public ItemController(ItemRepository repository, ItemsAssembler itemsAssembler) {
        this.repository = repository;
        this.itemsAssembler = itemsAssembler;
    }

    @GetMapping(produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAllProduct(Pageable pageable, PagedResourcesAssembler assembler) {

        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());

        List<Item> items = repository.selectAll();

        PageInfo<Item> pageInfo = new PageInfo<>(items);

        List<ItemResource> resources = itemsAssembler.toResources(items);

        Page<ItemResource> page = new PageImpl<>(resources, pageable, pageInfo.getTotal());

        return ResponseEntity.ok(assembler.toResource(page));

    }

    @GetMapping(value = "/{prodId}", produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<ItemResource> getProduct(@PathVariable int prodId) {
        Item item = repository.selectByProdId(prodId);

        return ResponseEntity.ok(itemsAssembler.toResource(item));

    }

}
