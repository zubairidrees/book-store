package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.DiscountDTO;

import java.util.List;


/**
 * The Interface DiscountService.
 * 
 * @author Zubair.Idrees
 */
public interface DiscountService {

	/**
	 * Gets the all discounts.
	 *
	 * @return the all discounts
	 */
	List<DiscountDTO> getAllDiscounts();

	/**
	 * Save discount.
	 *
	 * @param discountDTO the discount DTO
	 * @return the discount DTO
	 */
	DiscountDTO saveDiscount(DiscountDTO discountDTO);

	/**
	 * Gets the discount by Book Classification and promo code.
	 *
	 * @param bookClassification the Book Classification
	 * @param promoCode the promo code
	 * @return the discount by Book Classification and promo code
	 */
	DiscountDTO getDiscountBybookClassificationAndPromoCode(Long bookClassification, String promoCode);

	/**
	 * Gets the discount by Book Classification and promo id.
	 *
	 * @param bookClassificationId the Book Classification id
	 * @param promoId the promo id
	 * @return the discount by Book Classification and promo id
	 */
	DiscountDTO getDiscountBybookClassificationAndPromoId(Long bookClassificationId, Long promoId);
}
