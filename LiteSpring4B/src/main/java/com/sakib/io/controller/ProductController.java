package com.sakib.io.controller;


import com.sakib.io.litespring.annotation.*;
import com.sakib.io.litespring.context.UserContext;
import com.sakib.io.litespring.enums.MethodType;
import com.sakib.io.litespring.annotation.*;
import com.sakib.io.models.Product;
import com.sakib.io.models.dto.AddProductRequest;
import com.sakib.io.models.dto.AddProductResponse;
import com.sakib.io.service.ProductService;


@Component
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(url = "/api/products", type = MethodType.POST)
    public AddProductResponse addProduct(@RequestBody AddProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());

        String id = productService.addProduct(product);

        String ctx = UserContext.getUserContext();


        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setId(id);

        return addProductResponse;
    }

    @RequestMapping(url = "/api/products/{id}", type = MethodType.GET)
    public Product getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }

}
