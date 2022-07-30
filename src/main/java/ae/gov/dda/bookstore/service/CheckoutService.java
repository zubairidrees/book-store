package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.ShoppingCartDTO;


/**
 * The Interface CheckoutService.
 * 
 * @author Zubair.Idrees
 */
public interface CheckoutService {

	/**
	 * Checkout card.
	 *
	 * @param shoppingCartDTO the shopping cart DTO
	 * @return the shopping cart VO
	 */
	Double checkout(ShoppingCartDTO shoppingCartDTO);
	
}