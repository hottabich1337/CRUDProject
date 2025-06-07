package com.example.CRUDProject.controller;


import com.example.CRUDProject.dto.ProductDTO;
import com.example.CRUDProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam Integer id,@RequestBody ProductDTO productDTO ) {
        productService.updateProduct(id,productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Integer> deleteProduct(@RequestParam Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/productInfo")
    public ResponseEntity<ProductDTO> getProductInfo(@RequestParam Integer id) {
        return ResponseEntity.ok(productService.productInfo(id));
    }
}

