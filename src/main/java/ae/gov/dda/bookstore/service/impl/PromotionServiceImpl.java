package ae.gov.dda.bookstore.service.impl;

import ae.gov.dda.bookstore.dto.PromotionDTO;
import ae.gov.dda.bookstore.mapper.PromotionMapper;
import ae.gov.dda.bookstore.model.Promotion;
import ae.gov.dda.bookstore.repository.PromotionRepository;
import ae.gov.dda.bookstore.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The Class Promotion Service Implementation.
 * 
 * @author Zubair.Idrees
 *
 */
@Slf4j
@Service
public class PromotionServiceImpl implements PromotionService {

	/** The promotion repository. */
	private final PromotionRepository promotionRepository;


	/**
	 * Instantiates a new promotion service impl.
	 *
	 * @param promotionRepository the promotion repository
	 */
	public PromotionServiceImpl(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	/**
	 * Find all promotions.
	 *
	 * @return the list
	 */
	@Override
	public List<PromotionDTO> findAllPromotions() {
		List<Promotion> promotions = promotionRepository.findAll();

		return PromotionMapper.INSTANCE.convertPromotions(promotions);
	}

	/**
	 * Save promotion.
	 *
	 * @param promotionDTO the promotion DTO
	 * @return the promotion DTO
	 */
	@Override
	public PromotionDTO savePromotion(PromotionDTO promotionDTO) {
		Promotion promotion = PromotionMapper.INSTANCE.convertToEntity(promotionDTO);
		promotion = promotionRepository.save(promotion);
		return PromotionMapper.INSTANCE.convertToDto(promotion);
	}

	/**
	 * Gets the promotion by code.
	 *
	 * @param promoCode the promo code
	 * @return the promotion by code
	 */
	@Override
	public PromotionDTO getPromotionByCode(String promoCode) {
		log.info("BookService:getPromotionByCode("+promoCode+")...");
		Promotion promotion = promotionRepository.findByPromoCodeIgnoreCase(promoCode);
		if( promotion != null ) {
			return PromotionMapper.INSTANCE.convertToDto(promotion);
		}
		return null;
	}

}
