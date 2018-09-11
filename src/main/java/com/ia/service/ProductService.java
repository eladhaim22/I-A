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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Value("${files.folder}")
    private String filesFolder;

    @Value("${images.folder}")
    private String imagesFolder;

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
                productDTO.setImageUrl(imagesFolder + productDTO.getFile().getOriginalFilename());
            }
            Product product = productMapper.toModel(productDTO);
            productRepository.saveAndFlush(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
