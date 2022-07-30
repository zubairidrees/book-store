package ae.gov.dda.bookstore.service.impl;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.exception.BookNotFoundException;
import ae.gov.dda.bookstore.mapper.BookMapper;
import ae.gov.dda.bookstore.model.Book;
import ae.gov.dda.bookstore.repository.BookRepository;
import ae.gov.dda.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The Class Book Service Implementation.
 * 
 * @author Zubair.Idrees
 *
 */

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	/** The book repository. */
	private final BookRepository bookRepository;


	/**
	 * Instantiates a new book service impl.
	 *
	 * @param bookRepository the book repository
	 */
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * Find all books.
	 *
	 * @return the list
	 */
	@Override
	public List<BookDTO> findAllBooks() {
		List<Book> books = bookRepository.findAll();

		return BookMapper.INSTANCE.convertBooks(books);
	}

	/**
	 * Gets the book by id.
	 *
	 * @param bookId the book id
	 * @return the book by id
	 */
	public BookDTO getBookById(Long bookId) {
		log.info("BookService:getBookById("+bookId+")...");
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		if(bookOpt.isPresent())
			return BookMapper.INSTANCE.convertIntoDto(bookOpt.get());
		return null;
	}

	/**
	 * Find book by id.
	 *
	 * @param bookId the book id
	 * @return the optional
	 */
	@Override
	public Optional<Book> findBookById(Long bookId) {
		return bookRepository.findById(bookId);
	}

	/**
	 * Update book.
	 *
	 * @param bookDTO the book DTO
	 * @return the book DTO
	 */
	@Override
	public BookDTO updateBook(BookDTO bookDTO) {

		Book exBook = bookRepository.getById(bookDTO.getBookId());
		if (exBook != null) {
			Book book = BookMapper.INSTANCE.convertIntoEntity(bookDTO);
			book = bookRepository.save(book);
			return BookMapper.INSTANCE.convertIntoDto(book);
		} else {
			throw new BookNotFoundException(bookDTO.getBookId());
		}
	}

	/**
	 * Delete book.
	 *
	 * @param bookId the book id
	 */
	@Override
	@Transactional
	public void deleteBook(Long bookId) {
		log.info("BookService:deleteBook(" + bookId + ")...");
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		if (bookOpt.isPresent())
			bookRepository.delete(bookOpt.get());
		else {
			log.info("Book [id=" + bookId + "] not found!");
		}
		log.info("Book [id=" + bookId + "] deleted successfully!");
	}

	/**
	 * Save book.
	 *
	 * @param bookDTO the book DTO
	 * @return the book DTO
	 */
	@Override
	public BookDTO saveBook(BookDTO bookDTO) {
		Book book = BookMapper.INSTANCE.convertIntoEntity(bookDTO);
		book = bookRepository.save(book);

		return BookMapper.INSTANCE.convertIntoDto(book);
	}

	/**
	 * Gets the book by name.
	 *
	 * @param bookName the book name
	 * @return the book by name
	 */
	@Override
	public BookDTO getBookByName(String bookName) {
		log.info("BookService:getBookByName(" + bookName + ")...");
		Book book = bookRepository.findByNameIgnoreCase(bookName);
		if (book != null) {
			return BookMapper.INSTANCE.convertIntoDto(book);
		}
		return null;
	}

}
