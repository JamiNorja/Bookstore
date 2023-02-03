package Bookstore.Harjoitus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Bookstore.Harjoitus.domain.Book;
import Bookstore.Harjoitus.domain.BookRepository;

@SpringBootApplication
public class HarjoitusApplication {
	
	private static final Logger log = LoggerFactory.getLogger(HarjoitusApplication.class);	

	public static void main(String[] args) {
		SpringApplication.run(HarjoitusApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstore(BookRepository bookRepository) {
		return (args) -> {
			
			log.info("create books");
			bookRepository.save(new Book("Kasvoton kuolema", "Hennig Mankell", "lfkädalkdsfäöl", 2002, 12.0, 1));
			bookRepository.save(new Book("Riian verikoirat", "Hennig Mankell", "122332343", 2003, 13, 2));
			
			log.info("fetch all books from the database");
			for (Book book : bookRepository.findAll()) {
				System.out.println("kirja: " + book.toString());
			}
		};
	}
	
	
}
