package ae.gov.dda.bookstore.exception;


/**
 * The Class BookNotFoundException.
 * 
 * @author Zubair.Idrees
 */
public class BookNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3854036377137435009L;

	/**
	 * Instantiates a new book not found exception.
	 *
	 * @param bookId the book id
	 */
	public BookNotFoundException(Long bookId ) {
		super("Book [id="+bookId+"] not found.");
	}

}
