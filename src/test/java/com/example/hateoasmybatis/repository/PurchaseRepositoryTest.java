package com.example.hateoasmybatis.repository;

import com.example.hateoasmybatis.model.dto.Purchase;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseRepositoryTest {

    @Autowired
    private PurchaseRepository repository;


    @Test
    public void selectByChargeNo() {
        String chargeNo = "201901142869211";
        Purchase purchase = repository.selectByChargeNo(chargeNo);
        log.info("# purchase : {}", purchase);
    }

    @Test
    public void selectAllByUserId() {
        PageHelper.startPage(1, 10);
        List<Purchase> purchases = repository.selectAllByUserId("gilju81");

        log.info("purchase : {}", purchases.get(0));
        log.info("purchase size : {}", purchases.size());

    }
}