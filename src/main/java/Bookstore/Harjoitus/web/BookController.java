package Bookstore.Harjoitus.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//Listaa kirjat
	@RequestMapping(value = { "/booklist", "/" })
	public String showBooklist(Model model) {
		log.info("get books from db");
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	//Lisää kirjan
	@GetMapping("/addBook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", categoryRepository.findAll());
		return "newBook";
	}
	
	//Tallentaa kirjan
	@PostMapping("saveBook")
	public String saveBook(Book book) {
		
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	//Poistaa kirjan
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:/booklist";
	}
	
	//Muokkaa kirjan
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("categorys", categoryRepository.findAll());
		return "editBook";
	}
	
	//Rest kaikki kirjat
	@GetMapping("/books")
	public @ResponseBody List<Book> showRestBooks() {
		log.info("showRestBooks");
		return (List<Book>) bookRepository.findAll();
	}
	
	//Rest kirja id:n mukaan
	@GetMapping("/book/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return bookRepository.findById(id);
	}
	
}
