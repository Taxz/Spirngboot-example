package edu.example.study.controller;

import edu.example.study.entity.Product;
import edu.example.study.service.ProductService;
import edu.example.study.util.CommonResponse;
import edu.example.study.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Taxz on 2018/4/18.
 */
@RestController
public class Controll {

    @Autowired
    ProductService productService;

    @GetMapping("/api/{id}")
    public CommonResponse getProduct(@PathVariable("id") Long id) throws Exception {
        return ResponseUtil.generateResponse(productService.select(id));
    }

    @GetMapping("/api/get")
    public CommonResponse getAll() {
        return ResponseUtil.generateResponse(productService.getAll());
    }

    @PutMapping("/api/put")
    public CommonResponse updateProduct(@RequestBody Product product) throws Exception {
        return ResponseUtil.generateResponse(productService.update(product));
    }

    @DeleteMapping("/api/{id}")
    public CommonResponse deleteProduct(@PathVariable("id") long id) throws Exception {
        return ResponseUtil.generateResponse(productService.delete(id));
    }

    @PostMapping("/api/add")
    public CommonResponse addProduce(@RequestBody Product product) throws Exception {
        return ResponseUtil.generateResponse(productService.add(product));
    }
}
