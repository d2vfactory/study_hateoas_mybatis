package com.example.hateoasmybatis.repository;

import com.example.hateoasmybatis.model.dto.Package;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageRepositoryTest {

    @Autowired
    private PackageRepository repository;

    @Test
    public void selectByProdId_1202142() {
        int prodId = 1202142;
        String prodName = "스텝 바이 스텝";

        Package packageMst = repository.selectByProdId(prodId);

        log.info("# package : {}", packageMst);

        assertThat(packageMst.getProdName()).isEqualTo(prodName);
    }

    @Test
    public void selectAll_pageSize10() {
        PageHelper.startPage(1, 10);
        List<Package> packages = repository.selectAll();

        assertThat(packages.size()).isEqualTo(10);
        PageInfo<Package> pagePackage = new PageInfo<>(packages);

        assertThat(pagePackage.getPageSize()).isEqualTo(10);
    }


}