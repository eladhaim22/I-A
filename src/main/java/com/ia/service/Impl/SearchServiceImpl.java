package com.ia.service.Impl;

import com.ia.dto.ProductDTO;
import com.ia.entity.Product;
import com.ia.mappers.ProductMapper;
import com.ia.service.SearchService;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @PersistenceContext
    private EntityManager centityManager;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> fuzzySearch(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onField("name")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);
        List<Product> products = null;
        try {
            products = jpaQuery.getResultList();

        } catch (NoResultException nre) {
            products = new ArrayList<>();
        }
        return products.stream().map(p -> productMapper.toDTO(p)).collect(Collectors.toList());
    }
}