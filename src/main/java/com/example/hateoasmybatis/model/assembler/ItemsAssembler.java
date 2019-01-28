package com.example.hateoasmybatis.model.assembler;

import com.example.hateoasmybatis.controller.ItemController;
import com.example.hateoasmybatis.model.dto.Item;
import com.example.hateoasmybatis.model.resource.ItemResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ItemsAssembler extends ResourceAssemblerSupport<Item, ItemResource> {


    public ItemsAssembler() {
        super(ItemController.class, ItemResource.class);
    }

    @Override
    public ItemResource toResource(Item item) {
        ItemResource itemResource = new ItemResource(item);

        itemResource.add(linkTo(methodOn(ItemController.class).getProduct(item.getProdId()))
                .withSelfRel());

        return itemResource;
    }
}
