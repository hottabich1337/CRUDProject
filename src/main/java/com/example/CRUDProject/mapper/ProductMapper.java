package com.example.CRUDProject.mapper;


import com.example.CRUDProject.dto.OrderDTO;
import com.example.CRUDProject.dto.ProductDTO;
import com.example.CRUDProject.entity.Order;
import com.example.CRUDProject.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product ProductDTOToProduct(ProductDTO productDTO);
    ProductDTO ProductToProductDTO(Product product);
}
