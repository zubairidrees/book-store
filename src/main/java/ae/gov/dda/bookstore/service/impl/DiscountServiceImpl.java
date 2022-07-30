package ae.gov.dda.bookstore.service.impl;

import ae.gov.dda.bookstore.dto.DiscountDTO;
import ae.gov.dda.bookstore.mapper.DiscountMapper;
import ae.gov.dda.bookstore.model.Discount;
import ae.gov.dda.bookstore.repository.DiscountRepository;
import ae.gov.dda.bookstore.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The Class Discount Service Implementation.
 * 
 * @author Zubair.Idrees
 */
@Slf4j
@Service
public class DiscountServiceImpl implements DiscountService {

	/** The discount repository. */
	private final DiscountRepository discountRepository;


	/**
	 * Instantiates a new discount service impl.
	 *
	 * @param discountRepository the discount repository
	 */
	public DiscountServiceImpl(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	/**
	 * Gets the all discounts.
	 *
	 * @return the all discounts
	 */
	@Override
	public List<DiscountDTO> getAllDiscounts() {
		List<Discount> discounts = discountRepository.findAll();

		return DiscountMapper.INSTANCE.convertDiscounts(discounts);
	}

	/**
	 * Save discount.
	 *
	 * @param discountDTO the discount DTO
	 * @return the discount DTO
	 */
	@Override
	public DiscountDTO saveDiscount(DiscountDTO discountDTO) {
		Discount discount = DiscountMapper.INSTANCE.convertToEntity(discountDTO);
		discount = discountRepository.save(discount);
		return DiscountMapper.INSTANCE.convertToDto(discount);
	}
	
	/**
	 * Gets the discount by Book Classification and promo code.
	 *
	 * @param bookClassification the Book Classification
	 * @param promoCode the promo code
	 * @return the discount by Book Classification and promo code
	 */
	@Override
	public DiscountDTO getDiscountBybookClassificationAndPromoCode(Long bookClassification, String promoCode) {
		log.info("DiscountServiceImpl.getDiscountBybookClassificationAndPromoCode(bookClassification="+bookClassification+", promoCode="+promoCode+")...");
		DiscountDTO discountDTO = null;
		Discount discount = discountRepository.findBybookClassificationAndPromoCode(bookClassification, promoCode);
		if(discount != null) {
			discountDTO = DiscountMapper.INSTANCE.convertToDto(discount);
		}
		return discountDTO;
	}
	
	/**
	 * Gets the discount by Book Classification and promo id.
	 *
	 * @param bookClassificationId the Book Classification id
	 * @param promoId the promo id
	 * @return the discount by Book Classification and promo id
	 */
	@Override
	public DiscountDTO getDiscountBybookClassificationAndPromoId(Long bookClassificationId, Long promoId) {
		log.info("DiscountServiceImpl.getDiscountBybookClassificationAndPromoId(bookClassificationId="+bookClassificationId+", promoId="+promoId+")...");
		DiscountDTO discountDTO = null;
		Discount discount = discountRepository.findBybookClassificationAndPromoId(bookClassificationId, promoId);
		if(discount != null) {
			discountDTO = DiscountMapper.INSTANCE.convertToDto(discount);
		}
		return discountDTO;
	}

}
