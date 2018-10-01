package com.ia;

import com.ia.dto.ProductDTO;
import com.ia.entity.Product;
import com.ia.mappers.ProductMapper;
import com.ia.repository.ProductRepository;
import com.ia.service.Impl.ProductServiceImpl;
import com.ia.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	private List<Product> products = new ArrayList<>();
	private List<ProductDTO> productsDTO = new ArrayList<>();

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@MockBean
	private ProductRepository productRepository;

	@TestConfiguration
	static class ProductServiceImplTestContextConfiguration {

		@Bean
		public ProductService productService() {
			ProductService productService = new ProductServiceImpl();
			return productService;
		}

		@Bean
		public ProductMapper productMapper() {
			ProductMapper productMapper = new ProductMapper();
			return productMapper;
		}
	}

	@Before
	public void setUp() {
		ReflectionTestUtils.setField(productService, "filesFolder", "src/main/webapp/product_images/");
		ReflectionTestUtils.setField(productMapper, "filesServer", "http://localhost:8080/product_images/");
		BuildProductsList();
		BuildProductsDTOList();
		Mockito.when(productRepository.getOne(1))
				.thenReturn(products.get(0));

		Mockito.when(productRepository.findAll()).thenReturn(products);
	}

	@Test
	public void canGetByIdAndMapToDTO() throws Exception {
		ProductDTO p = productService.getById(1);
		ProductDTO expected = productsDTO.get(0);
		assertThat(p,sameBeanAs(expected));
	}

	@Test
	public void cannotGetById() {
		try {
			ProductDTO p = productService.getById(10);
		}
		catch(Exception e){
			Assert.assertEquals(e.getMessage(),"El producto no existe" );
		}
	}

	@Test
	public void canGetAllAndMapToListDTO(){
		List<ProductDTO> products = productService.getAll();
		int index = 0;
		assertThat(products, sameBeanAs(productsDTO));
 	}

	@Test
	public void canSaveProduct(){
		List<ProductDTO> products = productService.getAll();
		int index = 0;
		assertThat(products, sameBeanAs(productsDTO));
	}

	private void BuildProductsList(){
		Product p = null;
		p = new Product(){{
				setId(1);
				setDescription("test-description");
				setPrice(10);
				setActive(true);
				setSku("1000");
				setName("test-name");
				setFileName("test-filename");
			}};

		products.add(p);

		p = new Product(){{
				setId(2);
				setDescription("test-description");
				setPrice(101);
				setActive(true);
				setSku("1001");
				setName("test-name");
				setFileName("test-filename");
			}};
		products.add(p );
	}

	private void BuildProductsDTOList(){
		ProductDTO p = null;
		p  = new ProductDTO();
		p.setDescription("test-description");
		p.setImageUrl("http://localhost:8080/product_images/test-filename");
		p.setPrice(10);
		p.setActive(true);
		p.setName("test-name");
		p.setSku("1000");
		p.setFileName("test-filename");
		p.setId(1);
		productsDTO.add(p);

		p  = new ProductDTO();
		p.setId(2);
		p.setDescription("test-description");
		p.setImageUrl("http://localhost:8080/product_images/test-filename");
		p.setPrice(101);
		p.setActive(true);
		p.setSku("1001");
		p.setName("test-name");
		p.setFileName("test-filename");
		productsDTO.add(p);
	}
}
