package ae.gov.dda.bookstore.repository;

import ae.gov.dda.bookstore.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The Interface PromotionRepository.
 * 
 * @author Zubair.Idrees
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

	
	/**
	 * Find by promo id.
	 *
	 * @param promoId the promo id
	 * @return the Book Classification
	 */
	Promotion findByPromoId(Long promoId);
	
	/**
	 * Find by promo code ignore case.
	 *
	 * @param promoCode the promo code
	 * @return the promotion
	 */
	Promotion findByPromoCodeIgnoreCase(String promoCode);


}
