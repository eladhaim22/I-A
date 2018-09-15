package com.ia.service.Impl;

import com.ia.dto.ProductDTO;
import com.ia.entity.Product;
import com.ia.mappers.ProductMapper;
import com.ia.repository.ProductRepository;
import com.ia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Value("${files.folder}")
    private String filesFolder;

    public ProductDTO getById(Integer id) throws Exception {
        Product product = productRepository.getOne(id);
        if(product != null){
            return productMapper.toDTO(product);
        }
        else {
            throw new Exception("El producto no existe");
        }
    }

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> productMapper.toDTO(p)).collect(Collectors.toList());
    }

    public void delete(Integer id){
        productRepository.deleteById(id);
    }

    public void save(ProductDTO productDTO){
        try {
            if(!productDTO.getFile().isEmpty()){
                FileOutputStream output = new FileOutputStream(filesFolder + productDTO.getFile().getOriginalFilename());
                output.write(productDTO.getFile().getBytes());
                productDTO.setFileName(productDTO.getFile().getOriginalFilename());
            }
            Product product = productMapper.toModel(productDTO);
            productRepository.saveAndFlush(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
