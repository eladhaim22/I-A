package com.ia.mappers;

import com.ia.dto.ProductDTO;
import com.ia.entity.Product;
import com.ia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IMapper<Product,ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Value("${files.server}")
    private String filesServer;

    @Override
    public ProductDTO toDTO(Product model) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(model.getId());
        productDTO.setSku(model.getSku());
        productDTO.setName(model.getName());
        productDTO.setActive(model.isActive());
        productDTO.setPrice(model.getPrice());
        productDTO.setFileName(model.getFileName());
        productDTO.setImageUrl(filesServer + model.getFileName());
        productDTO.setDescription(model.getDescription());
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
        product.setSku(dto.getSku());
        product.setActive(dto.isActive());
        product.setPrice(dto.getPrice());
        if(dto.getFileName() != null){
            product.setFileName(dto.getFileName());
        }
        product.setDescription(dto.getDescription());
        return product;
    }
}
