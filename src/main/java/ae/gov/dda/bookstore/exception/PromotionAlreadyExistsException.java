package ae.gov.dda.bookstore.exception;


/**
 * The Class PromotionAlreadyExistsException.
 * 
 * @author Zubair.Idrees
 */
public class PromotionAlreadyExistsException extends RuntimeException {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6516695169846345377L;

	/**
	 * Instantiates a new promotion already exists exception.
	 *
	 * @param promoCode the promo code
	 */
	public PromotionAlreadyExistsException(String promoCode) {
		super("Promotion [code="+promoCode+"] already exists.");
	}

}
