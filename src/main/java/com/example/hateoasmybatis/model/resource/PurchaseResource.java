package com.example.hateoasmybatis.model.resource;

import com.example.hateoasmybatis.controller.PurchaseController;
import com.example.hateoasmybatis.model.dto.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Setter
@Getter
@NoArgsConstructor
@Relation(collectionRelation = "purchases")
public class PurchaseResource extends ResourceSupport {

    private Purchase purchase;

    public PurchaseResource(Purchase purchase) {
        this.purchase = purchase;

        /*
        "_links": {
            "purchases": {
                "href": "http://127.0.0.1:8080/rest/purchases?userId=gilju81"
            },
            "self": {
                "href": "http://127.0.0.1:8080/rest/purchases/201901142869211"
            }
        }
         */

        add(linkTo(methodOn(PurchaseController.class).getPurchase(purchase.getChargeNo()))
                .withSelfRel());
    }

}
