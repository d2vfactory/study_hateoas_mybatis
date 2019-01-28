package com.example.hateoasmybatis.model.assembler;

import com.example.hateoasmybatis.controller.PackageController;
import com.example.hateoasmybatis.model.dto.Package;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PackageAssembler extends ResourceAssemblerSupport<Package, ResourceSupport> {

    public PackageAssembler() {
        super(Package.class, ResourceSupport.class);
    }


    @Override
    public Resource<Package> toResource(Package pkg) {
        Resource<Package> packageResource = new Resource<>(pkg);

        Link self = linkTo(methodOn(PackageController.class).getProduct(pkg.getProdId())).withSelfRel();
        packageResource.add(self);

        return packageResource;
    }
}
