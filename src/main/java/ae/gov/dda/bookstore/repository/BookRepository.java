package ae.gov.dda.bookstore.repository;

import ae.gov.dda.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The Interface BookRepository.
 * 
 * @author Zubair.Idrees
 */
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * Find by book id.
	 *
	 * @param bookId the book id
	 * @return the book
	 */
	Book findByBookId(Long bookId);

	/**
	 * Find by name ignore case.
	 *
	 * @param name the name
	 * @return the book
	 */
	Book findByNameIgnoreCase(String name);

}
