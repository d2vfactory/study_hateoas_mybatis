package com.example.hateoasmybatis.controller;

import com.example.hateoasmybatis.model.dto.Purchase;
import com.example.hateoasmybatis.model.resource.PurchaseResource;
import com.example.hateoasmybatis.repository.PurchaseRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/rest/purchases")
@RestController
public class PurchaseController {

    private final PurchaseRepository repository;

    public PurchaseController(PurchaseRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAllPurchases(Pageable pageable,
                                             PagedResourcesAssembler<PurchaseResource> assembler,
                                             @RequestParam(value = "userId", defaultValue = "") String userId) {


        // pagable => 시작 페이지 : 0 (hal 규격)
        // pageHelper => 시작 페이지 : 1
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());

        List<Purchase> list;
        if(userId.isEmpty()) {
            list = repository.selectAll();
        } else {
            list = repository.selectAllByUserId(userId);
        }


        PageInfo<Purchase> pageInfo = new PageInfo<>(list);

        Page<Purchase> page = new PageImpl<>(list, pageable, pageInfo.getTotal());

        return ResponseEntity.ok(assembler.toResource(page.map(PurchaseResource::new)));

    }

    @GetMapping(value = "/{chargeNo}", produces = {MediaTypes.HAL_JSON_UTF8_VALUE})
    public ResponseEntity<PurchaseResource> getPurchase(@PathVariable String chargeNo) {
        // purchase to purchaseResource
        Purchase purchase = repository.selectByChargeNo(chargeNo);
        PurchaseResource purchaseResource = new PurchaseResource(purchase);
        return ResponseEntity.ok(purchaseResource);

    }


}
