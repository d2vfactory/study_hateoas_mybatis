# hateoas with spring-mybatis

## API Info
- /rest

- sample url
    - http://127.0.0.1:8080/rest/purchases
    - http://127.0.0.1:8080/rest/packages
    - http://127.0.0.1:8080/rest/items


## Pagination
- MyBatis의 Paging 처리를 위해 PageHelper 사용.
- PagedResource / Page / Pageable를 사용하기 위해 spring-data-jdbc 추가.



## Resource (Link) 적용 방법
#### ResourceSupport 상속받아 사용.
> public class PurchaseResource extends ResourceSupport
- PurchaseResource 
    - instance 생성시 purchase 및 link 추가
- Purchase(Entity) 에서 직접 상속받아 사용할 수도 있음
    - controller 또는 service 단에서 link 추가

#### Resource와 ResourceAssemblerSupport 사용
> public class ItemResource extends Resource<Item>
> public class ItemAssembler extends ResourceAssemblerSupport<Item, ItemResource>
- ItemAssembler
    - toResource(Item item) 에서 link를 포함하여 ItemResource 반환

## Controller
#### Page<Entity> type을 Page<EntitySupport>로 변경방법
- ResourceSupport를 상속받아 사용한 경우
```$java
# Page 생성
Page<Purchase> page = new PageImpl<>(allByUserId, pageable, pageInfo.getTotal());

# Page<Purchase> to Page<PurchaseResource>
page.map(PurchaseResource::new);

```
- Resource와 ResourceAssemblerSupport를 사용한 경우
```$java
List<Item> items = repository.selectAll();

List<ItemResource> resources = itemsAssembler.toResources(items);

```

#### PagedResource 사용
> PagedResourcesAssembler assembler
- assembler.toResource(Page entity)
```$java
Page<ItemResource> page = new PageImpl<>(resources, pageable, pageInfo.getTotal());
return ResponseEntity.ok(assembler.toResource(page));
```

## 참고해볼만한것.
> https://dzone.com/articles/spring-boot-hateoas-for-restful-services

> 백기선 스프링 HATEOAS 소개 : https://www.youtube.com/watch?v=Uc8CzBpHOjQ

