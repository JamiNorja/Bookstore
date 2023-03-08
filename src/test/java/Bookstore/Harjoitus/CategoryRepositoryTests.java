package Bookstore.Harjoitus;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Bookstore.Harjoitus.domain.Category;
import Bookstore.Harjoitus.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTests {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void findByNameShouldReturnName() {
		List<Category> categories = categoryRepository.findByName("Kauno");
		assertThat(categories.get(0).getName().equalsIgnoreCase("kauno"));
	}
	
	@Test
	public void findByName() {
		List<Category> categories = categoryRepository.findByName("Kauhu");
		assertThat(categories).hasSize(1);
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Toiminta");
		categoryRepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteNewCategory() {
		List<Category> categories = categoryRepository.findByName("Kauno");
		Category category = categories.get(0);
		categoryRepository.delete(category);
		List<Category> newCategories = categoryRepository.findByName("Kauno");
		assertThat(newCategories).hasSize(0);
	}

}
