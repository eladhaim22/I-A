package com.ia.service;

import com.ia.dto.ProductDTO;
import com.ia.entity.Product;
import com.ia.mappers.ProductMapper;
import com.ia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public interface ProductService {
     ProductDTO getById(Integer id) throws Exception;
     List<ProductDTO> getAll();
     List<ProductDTO> getAllActive();
     void delete(Integer id);
     void save(ProductDTO productDTO);
}
