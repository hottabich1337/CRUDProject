package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ProductDTO;
import com.example.CRUDProject.entity.Product;
import com.example.CRUDProject.mapper.ProductMapper;
import com.example.CRUDProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.ProductDTOToProduct(productDTO);
        productRepository.save(product);
    }

    public void updateProduct(Integer id,ProductDTO productDTO) {
        Product product = productRepository.findById(id).get();
        ProductDTO existDTO = productMapper.ProductToProductDTO(product);
        product.setName(productDTO.getName() != null ? productDTO.getName() : existDTO.getName());
        product.setDescription(productDTO.getDescription() != null ? productDTO.getDescription() : existDTO.getDescription());
        product.setPrice(productDTO.getPrice() != null ? productDTO.getPrice() : existDTO.getPrice());
        productRepository.save(product);
    }

    public ProductDTO productInfo(Integer id) {
        productRepository.findById(id).get().setName(productRepository.findById(id).get().getName());
        return productMapper.ProductToProductDTO(productRepository.findById(id).get());
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }



}
