package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.model.Book;
import ae.gov.dda.bookstore.model.BookClassification;
import ae.gov.dda.bookstore.repository.BookClassificationRepository;
import ae.gov.dda.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;


/**
 * The Class BookServiceTest.
 *
 * @author Zubair.Idrees
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BookServiceTest {

	/** The book service. */
	@Autowired
	private BookService bookService;

	/** The book repository. */
	@MockBean
	private BookRepository bookRepository;

	/** The Book Classification repository. */
	@MockBean
	private BookClassificationRepository bookClassificationRepository;

	/**
	 * Test get all books.
	 */
	@Test
	void testGetAllBooks() {

		BookClassification bookClassification1 = new BookClassification(1L, "CODE 1", "Book Classification 1", null, null);
		BookClassification bookClassification2 = new BookClassification(2L, "CODE 2", "Book Classification 2", null, null);

		Book book1 = new Book(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", bookClassification1, 250.00, "Book 1 Description");
		Book book2 = new Book(2l, "Where the Crawdads Sing", "Penguin", "0735219109", bookClassification2,350.00, "Book 2 Description");

		List<Book> books = new ArrayList<Book>(Arrays.asList(book1, book2));

		doReturn(books).when(bookRepository).findAll();

		List<BookDTO> bookVOs = bookService.findAllBooks();

		Assertions.assertNotNull(bookVOs, "Books should not be NULL");
		Assertions.assertTrue(!bookVOs.isEmpty(), "Books should not be Empty");
		Assertions.assertTrue(bookVOs.size() == 2, "Books size not correct");
	}

	/**
	 * Test get book by id.
	 */
	@Test
	void testGetBookById() {
		BookClassification bookClassification1 = new BookClassification(1L, "CODE 1", "Book Classification 1", null, null);
		Book book1 = new Book(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", bookClassification1, 250.00, "Book 1 Description");

		Optional<Book> bookOpt = Optional.of(book1);

		doReturn(bookOpt).when(bookRepository).findById(Mockito.anyLong());

		BookDTO bookDTO = bookService.getBookById(Mockito.anyLong());

		Assertions.assertNotNull(bookDTO, "Book should not be NULL");
		Assertions.assertTrue(bookDTO.getName().equals("It Ends with Us: A Novel"), "Discounts size not correct");
	}

	/**
	 * Test get book by name.
	 */
	@Test
	void testGetBookByName() {
		BookClassification bookClassification1 = new BookClassification(1L, "CODE 1", "Book Classification 1", null, null);
		Book book1 = new Book(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", bookClassification1, 250.00, "Book 1 Description");

		doReturn(book1).when(bookRepository).findByNameIgnoreCase(Mockito.anyString());

		BookDTO bookVO = bookService.getBookByName(Mockito.anyString());

		Assertions.assertNotNull(bookVO, "Book should not be NULL");
		Assertions.assertTrue(bookVO.getName().equals("It Ends with Us: A Novel"), "Discounts size not correct");
	}

	/**
	 * Test create book.
	 */
	@Test
	void testCreateBook() {
		BookClassification bookClassification1 = new BookClassification(1L, "CODE 1", "Book Classification 1", null, null);
		doReturn(bookClassification1).when(bookClassificationRepository).getById(Mockito.anyLong());

		Book book1 = new Book(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", bookClassification1, 250.00, "Book 1 Description");
		doReturn(book1).when(bookRepository).save(Mockito.any());

		BookDTO returnBook = bookService
				.saveBook(new BookDTO(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", 250.00, "Book 1 Description", 1l));

		Assertions.assertNotNull(returnBook, "Book should not be NULL");
	}

	/**
	 * Test update book.
	 */
	@Test
	void testUpdateBook() {
		BookClassification bookClassification1 = new BookClassification(1L, "CODE 1", "Book Classification 1", null, null);
		doReturn(bookClassification1).when(bookClassificationRepository).getById(Mockito.anyLong());

		Book book1 = new Book(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", bookClassification1, 250.00, "Book 1 Description");
		doReturn(book1).when(bookRepository).getById(Mockito.anyLong());
		doReturn(book1).when(bookRepository).save(Mockito.any());

		BookDTO returnBook = bookService
				.updateBook(new BookDTO(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", 550.00, "Book 1 Description", 1l));

		Assertions.assertNotNull(returnBook, "Book should not be NULL");
	}

	/**
	 * Test delete book.
	 */
	@Test
	void testDeleteBook() {
		BookClassification bookClassification1 = new BookClassification(1L, "CODE 1", "Book Classification 1", null, null);
		Book book1 = new Book(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", bookClassification1, 250.00, "Book 1 Description");

		Optional<Book> bookOpt = Optional.of(book1);

		doReturn(bookOpt).when(bookRepository).findById(Mockito.anyLong());

		bookService.deleteBook(Mockito.anyLong());
	}

}
