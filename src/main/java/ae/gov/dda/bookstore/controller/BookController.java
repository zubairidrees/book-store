package ae.gov.dda.bookstore.controller;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.exception.BookAlreadyExistsException;
import ae.gov.dda.bookstore.exception.BookNotFoundException;
import ae.gov.dda.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing Book.
 * 
 * @author Zubair.Idrees
 */

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

	/** The book service. */
	private BookService bookService;

	/**
	 * Instantiates a new book controller.
	 *
	 * @param bookService the book service
	 */
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * POST /books : Create a new book.
	 *
	 * @param bookDTO the book to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         book, or with status 400 (Bad Request) if the book has already an ID
	 */
	@Operation(summary = "Create Book")
	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDTO) {
		log.info("BookController:createBook()...");
		if (bookDTO.getBookId() != null) {
			log.info("BookController:createBook() book Id not null");
			throw new BookAlreadyExistsException(bookDTO.getName());
		}
		BookDTO resultBook = bookService.saveBook(bookDTO);
		log.info("BookController:createBook() book saved");
		return new ResponseEntity<>(resultBook, HttpStatus.CREATED);
	}

	/**
	 * PUT /books : Updates an existing book.
	 *
	 * @param bookDTO the book DTO
	 * @return the ResponseEntity with state 200 (OK) and with body the updated
	 *         book, or with status 400 (Bad Request) if the book is not valid
	 */
	@Operation(summary = "Update Book")
	@PutMapping
	public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
		log.info("BookController:updateBook()...");
		if (bookService.getBookById(bookDTO.getBookId()) == null) {
			log.warn("Book not found with id = {id}", bookDTO.getBookId());
			throw new BookNotFoundException(bookDTO.getBookId());
		}
		BookDTO resultBook = bookService.updateBook(bookDTO);
		log.info("BookController:updateBook() Book updated successfully");
		return new ResponseEntity<>(resultBook, HttpStatus.OK);
	}
	/**
	 * GET /books : get all the books.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list ob books in
	 *         body, or with status 204 (NO CONTENT) if there no books in repository
	 */
	@Operation(summary = "Get All Books")
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		log.info("BookController:getAllBooks()...");
		List<BookDTO> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			log.info("BookController:getAllBooks() no Books found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		log.info("BookController:getAllBooks() returning books");
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	/**
	 * GET /books/:id : get the "id" book.
	 *
	 * @param id the id of the book to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the book, or
	 *         with status 404 (NOT FOUND)
	 */
	@Operation(summary = "Get Book by Id")
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		log.info("BookController:getBookById()...");
		BookDTO bookDTO = bookService.getBookById(id);
		if (bookDTO == null) {
			log.warn("BookController:getBookById() Book not found");
			throw new BookNotFoundException(id);
		}
		log.info("BookController:getBookById() Book found, returning");
		return new ResponseEntity<>(bookDTO, HttpStatus.OK);
	}

	/**
	 * DELETE /books/:id : delete the "id" book.
	 *
	 * @param id the id of the book to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@Operation(summary = "Delete Book by Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<BookDTO> deleteBookById(@PathVariable Long id) {
		log.info("BookController:deleteBookById()...");
		BookDTO book = bookService.getBookById(id);
		if (book == null) {
			log.warn("BookController:deleteById() Book not found");
			throw new BookNotFoundException(id);
		}

		bookService.deleteBook(id);
		log.info("BookController:deleteBookById() Book having id={id} deleted successfully", id);
		return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
	}
}