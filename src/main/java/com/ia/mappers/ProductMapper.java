package com.ia.mappers;

import com.ia.dto.ProductDTO;
import com.ia.entity.Product;
import com.ia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IMapper<Product,ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO toDTO(Product model) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(model.getId());
        productDTO.setSku(model.getSku());
        productDTO.setName(model.getName());
        productDTO.setRepositionPoint(model.getRepositionPoint());
        productDTO.setQuantity(model.getQuantity());
        return productDTO;
    }

    @Override
    public Product toModel(ProductDTO dto) {
        Product product = null;
        if(dto.getId() != null){
            product = productRepository.getOne(dto.getId());
        }
        else{
            product = new Product();
        }
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setRepositionPoint(dto.getRepositionPoint());
        product.setSku(dto.getSku());
        return product;
    }
}
