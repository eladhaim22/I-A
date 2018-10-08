package com.ia;

import com.ia.dto.PersonDTO;
import com.ia.dto.ProductDTO;
import com.ia.dto.UserDTO;
import com.ia.entity.Person;
import com.ia.entity.Product;
import com.ia.entity.Role;
import com.ia.entity.User;
import com.ia.mappers.PersonMapper;
import com.ia.mappers.ProductMapper;
import com.ia.mappers.UserMapper;
import com.ia.repository.PersonRepository;
import com.ia.repository.ProductRepository;
import com.ia.repository.RoleRepository;
import com.ia.repository.UserRepository;
import com.ia.service.Impl.ProductServiceImpl;
import com.ia.service.Impl.UserServiceImpl;
import com.ia.service.ProductService;
import com.ia.service.UserService;
import org.assertj.core.util.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	private List<User> users = new ArrayList<>();
	private List<UserDTO> usersDTO = new ArrayList<>();

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private RoleRepository roleRepository;

	@MockBean
	private PersonRepository personRepository;

	@TestConfiguration
	static class UserrServiceImplTestContextConfiguration {

		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}

		@Bean
		public UserMapper userMapper() {
			return new UserMapper();
		}

		@Bean
		public PersonMapper personMapper() {
			return new PersonMapper();
		}

		@Bean
		public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}
	}

	@Before
	public void setUp() {
		BuildUserList();
		BuildUserDTOList();
		Mockito.when(userRepository.getOne(new Long(1)))
				.thenReturn(users.get(0));

		Mockito.when(userRepository.findAll()).thenReturn(users);
	}

	@Test
	public void canGetByIdAndMapToDTO() throws Exception {
		UserDTO u = userService.getById(new Long(1));
		UserDTO expected = usersDTO.get(0);
		assertThat(u,sameBeanAs(expected));
	}

	@Test
	public void cannotGetById() {
		try {
			UserDTO u = userService.getById(new Long(10));
		}
		catch(Exception e){
			Assert.assertEquals(e.getMessage(),"El usuario no existe" );
		}
	}

	@Test
	public void canGetAllAndMapToListDTO(){
		List<UserDTO> p = userService.getAll();
		assertThat(p,sameBeanAs(usersDTO));
 	}

 	private void BuildUserList(){
		User user = null;
		user = new User(){{
				setId(new Long(1));
				setActive(true);
				setEmail("test-1@test.com");
				setPerson(new Person(){{
					setId(1);
					setDni("1111111");
					setAddress("test-address-1");
				}});
				setName("test-name1");
				setLastName("test-lastname1");
				setPassword("password");
				Set<Role> roleSet = new HashSet<>();
				roleSet.add(new Role(1,"TEST_ROLE"));
				setRoles(roleSet);
		}};

		users.add(user);

		user = new User(){{
			setId(new Long(2));
			setActive(true);
			setEmail("test-2@test.com");
			setPerson(new Person(){{
				setId(2);
				setDni("2222222");
				setAddress("test-address-2");
			}});
			setName("test-name2");
			setLastName("test-lastname2");
			setPassword("password");
			Set<Role> roleSet = new HashSet<>();
			roleSet.add(new Role(1,"TEST_ROLE"));
			setRoles(roleSet);
		}};

		users.add(user);
	}

	private void BuildUserDTOList(){
		UserDTO userDTO = null;
		userDTO = new UserDTO();
		userDTO.setId(new Long(1));
		userDTO.setActive(true);
		userDTO.setEmail("test-1@test.com");
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1);
		personDTO.setDni("1111111");
		personDTO.setAddress("test-address-1");
		userDTO.setPerson(personDTO);
		userDTO.setName("test-name1");
		userDTO.setLastName("test-lastname1");
			Set<Integer> roleSet = new HashSet<>();
			roleSet.add(1);
		userDTO.setRoles(roleSet);

		usersDTO.add(userDTO);

		userDTO = new UserDTO();
		userDTO.setId(new Long(2));
		userDTO.setActive(true);
		userDTO.setEmail("test-2@test.com");
		personDTO = new PersonDTO();
		personDTO.setId(2);
		personDTO.setDni("2222222");
		personDTO.setAddress("test-address-2");
		userDTO.setPerson(personDTO);
		userDTO.setName("test-name2");
		userDTO.setLastName("test-lastname2");
		roleSet = new HashSet<>();
		roleSet.add(1);
		userDTO.setRoles(roleSet);

		usersDTO.add(userDTO);
	}
}
