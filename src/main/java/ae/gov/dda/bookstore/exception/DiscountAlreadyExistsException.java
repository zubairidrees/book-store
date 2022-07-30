package ae.gov.dda.bookstore.exception;


/**
 * The Class DiscountAlreadyExistsException.
 * 
 * @author Zubair.Idrees
 */
public class DiscountAlreadyExistsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -307181129148221649L;

	/**
	 * Instantiates a new discount already exists exception.
	 *
	 * @param bookClassification the Book Classification
	 * @param promoId the promo id
	 */
	public DiscountAlreadyExistsException(Long bookClassification, Long promoId) {
		super("Discount [promoId="+promoId+", bookClassification="+bookClassification+"] already exists.");
	}

}
