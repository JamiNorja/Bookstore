package Bookstore.Harjoitus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Bookstore.Harjoitus.domain.Book;
import Bookstore.Harjoitus.domain.BookRepository;
import Bookstore.Harjoitus.domain.Category;

@DataJpaTest
public class BookRepositoryTests {
	@Autowired
	BookRepository bookRepository;
	
	@Test
	public void findByAuthorShouldReturnAuthor() {
		List<Book> books = bookRepository.findByAuthor("Hennig Mankell");
		assertThat(books.get(0).getAuthor().equalsIgnoreCase("hennig mankell"));
	}
	
	@Test
	public void findBookTitle() {
		List<Book> books = bookRepository.findByTitle("Kasvoton kuolema");
		assertThat(books).hasSize(1);
	}
	
	@Test
	public void saveBook() {
		Book book = new Book();
		bookRepository.save(book);
		assertNotEquals(book.getId(), null);
	}
	
	@Test 
	public void createNewBook() {
		Book book = new Book("Hullujen huone", "Tikka Tammi", "1262", 2005, 10, 4, new Category("Draama"));
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewBook() {
		List<Book> books = bookRepository.findByAuthor("Hennig Mankell");
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBooks = bookRepository.findByAuthor("Hennig Mankell");
		assertThat(newBooks).hasSize(1);
	}
	
}
