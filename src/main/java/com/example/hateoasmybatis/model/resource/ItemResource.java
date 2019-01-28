package com.example.hateoasmybatis.model.resource;

import com.example.hateoasmybatis.model.dto.Item;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation = "items")
public class ItemResource extends Resource<Item> {

    public ItemResource(Item content, Link... links) {
        super(content, links);
    }

}
