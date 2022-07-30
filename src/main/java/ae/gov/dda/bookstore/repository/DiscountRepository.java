package ae.gov.dda.bookstore.repository;

import ae.gov.dda.bookstore.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * The Interface DiscountRepository.
 * 
 * @author Zubair.Idrees
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	
	/**
	 * Find by discount id.
	 *
	 * @param discountId the discount id
	 * @return the discount
	 */
	Discount findByDiscountId(Long discountId);
	
	/**
	 * Find by Book Classification and promo id.
	 *
	 * @param bookClassificationId the Book Classification id
	 * @param promoId the promo id
	 * @return the discount
	 */
	@Query("SELECT dis from Discount dis WHERE dis.bookClassification.bookClassificationId = :bookClassificationId AND dis.promotion.promoId = :promoId")
	Discount findBybookClassificationAndPromoId(
			@Param("bookClassificationId") Long bookClassificationId,
			@Param("promoId") Long promoId);
	
	/**
	 * Find by Book Classification and promo code.
	 *
	 * @param bookClassificationId the Book Classification id
	 * @param promoCode the promo code
	 * @return the discount
	 */
	@Query("SELECT dis from Discount dis WHERE dis.bookClassification.bookClassificationId = :bookClassificationId AND dis.promotion.promoCode = :promoCode")
	Discount findBybookClassificationAndPromoCode(
			@Param("bookClassificationId") Long bookClassificationId,
			@Param("promoCode") String promoCode);

}
