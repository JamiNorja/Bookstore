package Bookstore.Harjoitus.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Bookstore.Harjoitus.domain.Book;
import Bookstore.Harjoitus.domain.BookRepository;
import Bookstore.Harjoitus.domain.CategoryRepository;

@Controller
public class BookController {
private static final Logger log = LoggerFactory.getLogger(BookController.class);	
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = { "/main" } )
	public String showMainPage() {
		return "index";
	}
	
	@RequestMapping(value = { "/booklist", "/" })
	public String showBooklist(Model model) {
		log.info("get books from db");
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	@GetMapping("/addBook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", categoryRepository.findAll());
		return "newBook";
	}
	
	@PostMapping("saveBook")
	public String saveBook(Book book) {
		
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:/booklist";
	}
	
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("categorys", categoryRepository.findAll());
		return "editBook";
	}
	
}
