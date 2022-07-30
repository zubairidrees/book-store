package ae.gov.dda.bookstore.exception;


/**
 * The Class bookClassificationNotFoundException.
 * 
 * @author Zubair.Idrees
 */
public class BookClassificationNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8993780851860765040L;

	/**
	 * Instantiates a new Book Classification not found exception.
	 *
	 * @param typeId the type id
	 */
	public BookClassificationNotFoundException(Long typeId) {
		super("Book Classification [id="+typeId+"] not found.");
	}

}
