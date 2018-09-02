package com.ia.service;

import com.ia.dto.ProductDTO;
import com.ia.dto.UserDTO;
import com.ia.entity.Product;
import com.ia.entity.Role;
import com.ia.entity.User;
import com.ia.mappers.ProductMapper;
import com.ia.mappers.UserMapper;
import com.ia.repository.ProductRepository;
import com.ia.repository.RoleRepository;
import com.ia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

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
        Product product = productMapper.toModel(productDTO);
        productRepository.saveAndFlush(product);
    }
}
