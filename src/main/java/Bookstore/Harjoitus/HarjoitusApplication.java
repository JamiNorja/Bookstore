package Bookstore.Harjoitus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Bookstore.Harjoitus.domain.AppUser;
import Bookstore.Harjoitus.domain.AppUserRepository;
import Bookstore.Harjoitus.domain.Book;
import Bookstore.Harjoitus.domain.BookRepository;
import Bookstore.Harjoitus.domain.Category;
import Bookstore.Harjoitus.domain.CategoryRepository;

@SpringBootApplication
public class HarjoitusApplication {
	
	private static final Logger log = LoggerFactory.getLogger(HarjoitusApplication.class);	

	public static void main(String[] args) {
		SpringApplication.run(HarjoitusApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstore(BookRepository bookRepository, CategoryRepository categoryRepository, AppUserRepository urepository) {
		return (args) -> {
			
			log.info("create categorys");
			categoryRepository.save(new Category("Kauno"));
			categoryRepository.save(new Category("Kauhu"));
			categoryRepository.save(new Category("Rakkaus"));
			categoryRepository.save(new Category("Draama"));
			
			log.info("create books");
			bookRepository.save(new Book("Kasvoton kuolema", "Hennig Mankell", "lfkädalkdsfäöl", 2002, 12.0, 1, categoryRepository.findByName("Kauhu").get(0)));
			bookRepository.save(new Book("Riian verikoirat", "Hennig Mankell", "122332343", 2003, 13, 2,  categoryRepository.findByName("Rakkaus").get(0)));
			
			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$10$JokuQqVqYScOZ28NvRcqwOcCMxwFuQJ8DRSald/3ODN9hkBGa0p.O", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$83q6U7uFRHKg3G43nl4TmOdrxKEUJ8l.lDLtKkdV.M8Q/FGNuxXOS", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
	
}
