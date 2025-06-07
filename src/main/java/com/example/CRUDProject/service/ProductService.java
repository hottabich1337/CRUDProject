package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ProductDTO;
import com.example.CRUDProject.entity.Product;
import com.example.CRUDProject.mapper.ProductMapper;
import com.example.CRUDProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public void addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.ProductDTOToProduct(productDTO);
        productRepository.save(product);
    }

}
