package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.PromotionDTO;

import java.util.List;


/**
 * The Interface PromotionService.
 * 
 * @author Zubair.Idrees
 */
public interface PromotionService {

	/**
	 * Find all promotions.
	 *
	 * @return the list
	 */
	List<PromotionDTO> findAllPromotions();

	/**
	 * Save promotion.
	 *
	 * @param promotionDTO the promotion DTO
	 * @return the promotion DTO
	 */
	PromotionDTO savePromotion(PromotionDTO promotionDTO);

	/**
	 * Gets the promotion by code.
	 *
	 * @param promoCode the promo code
	 * @return the promotion by code
	 */
	PromotionDTO getPromotionByCode(String promoCode);
}
