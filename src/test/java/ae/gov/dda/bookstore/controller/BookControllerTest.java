package ae.gov.dda.bookstore.controller;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The Class BookControllerTest.
 * 
 */
@WebMvcTest(BookController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class BookControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The mapper. */
	@Autowired
	private ObjectMapper mapper;

	/** The book service. */
	@MockBean
	private BookService bookService;

	/** The book 1. */
	BookDTO book1;
	
	/** The book 2. */
	BookDTO book2;
	
	/** The book 3. */
	BookDTO book3;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		book1 = new BookDTO(1l, "It Ends with Us: A Novel", "Colleen Hoover", "1501110365", 250.00, "Book 1 Description", 1l);
		book2 = new BookDTO(2l, "Where the Crawdads Sing", "Penguin", "0735219109", 350.00, "Book 2 Description", 2l);
		book3 = new BookDTO(3l, "Book Lovers", "Berkley", "0593334833", 500.00, "Book 3 Description", 3l);
	}

	/**
	 * Test find all books.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testFindAllBooks() throws Exception {
		List<BookDTO> books = new ArrayList<BookDTO>(Arrays.asList(book1, book2, book3));

		Mockito.when(bookService.findAllBooks()).thenReturn(books);

		mockMvc.perform(MockMvcRequestBuilders.get("/books/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
	}

	/**
	 * Test find all books is empty.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testFindAllBooks_IsEmpty() throws Exception {
		List<BookDTO> books = new ArrayList<BookDTO>();

		Mockito.when(bookService.findAllBooks()).thenReturn(books);

		mockMvc.perform(MockMvcRequestBuilders.get("/books/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	/**
	 * Test find book.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testFindBook() throws Exception {
		Mockito.when(bookService.getBookById(book2.getBookId())).thenReturn(book2);

		mockMvc.perform(MockMvcRequestBuilders.get("/books/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Where the Crawdads Sing")));
	}

	/**
	 * Test find book not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testFindBook_NotFound() throws Exception {
		Mockito.when(bookService.getBookById(2l)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/books/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	/**
	 * Test create book success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testCreateBook_Success() throws Exception {
		book1.setBookId(null);
		book1.setName("New Book");
		Mockito.when(bookService.saveBook(book1)).thenReturn(book1);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/books/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(book1));

		mockMvc.perform(mockRequest).andExpect(status().isCreated());
	}

	/**
	 * Test update book success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateBook_Success() throws Exception {
		Mockito.when(bookService.getBookById(book1.getBookId())).thenReturn(book1);
		Mockito.when(bookService.updateBook(book1)).thenReturn(book1);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/books/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(book1));

		mockMvc.perform(mockRequest).andExpect(status().isOk());
	}

	/**
	 * Test update book not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateBook_NotFound() throws Exception {
		Mockito.when(bookService.getBookById(3l)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.put("/books").content(this.mapper.writeValueAsString(book1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	/**
	 * Test delete book success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteBook_Success() throws Exception {
		Mockito.when(bookService.getBookById(book1.getBookId())).thenReturn(book1);

		mockMvc.perform(MockMvcRequestBuilders.delete("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	/**
	 * Test delete book not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteBook_NotFound() throws Exception {
		Mockito.when(bookService.getBookById(2l)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.delete("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
