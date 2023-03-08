package Bookstore.Harjoitus;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Bookstore.Harjoitus.domain.AppUser;
import Bookstore.Harjoitus.domain.AppUserRepository;

@DataJpaTest
public class AppUserRepositoryTests {
	@Autowired
	AppUserRepository urepository;
	
	@Test
	public void findByUsernameShouldReturnUsername() {
		AppUser appUsers = urepository.findByUsername("admin");
		assertThat(appUsers.getUsername().equalsIgnoreCase("admin"));
	}
	
	@Test
	public void findUsername() {
		AppUser appUsers = urepository.findByUsername("admin");
		assertThat(appUsers);
	}
	
	@Test 
	public void createNewUser() {
		AppUser appUsers = new AppUser("test", "$2a$10$HuSrhTk34ZBO.xZT9V33POYyuAzla0ZZqMaDTNktjuGX/ENfixbVy", "TEST");
		urepository.save(appUsers);
		assertThat(appUsers.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewUser() {
		AppUser appUsers = urepository.findByUsername("user");
		AppUser appUser = appUsers;
		urepository.delete(appUser);
		AppUser newAppUsers = urepository.findByUsername("user");
		assertThat(newAppUsers);
	}

}
