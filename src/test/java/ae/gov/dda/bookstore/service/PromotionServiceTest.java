package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.PromotionDTO;
import ae.gov.dda.bookstore.model.Promotion;
import ae.gov.dda.bookstore.repository.PromotionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;


/**
 * The Class PromotionServiceTest.
 *
 * @author Zubair.Idrees
 */

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PromotionServiceTest  {

	/** The promotion service. */
	@Autowired
	private PromotionService promotionService;

	/** The promotion repository. */
	@MockBean
	private PromotionRepository promotionRepository;

	/**
	 * Test get all promotions.
	 */
	@Test
	void testGetAllPromotions() {
		Promotion promotion1 = new Promotion(1l, "Promo1", "Promo1", "Promo1");
		Promotion promotion2 = new Promotion(2l, "Promo2", "Promo2", "Promo2");
		List<Promotion> discounts = new ArrayList<Promotion>(Arrays.asList(promotion1, promotion2));

		doReturn(discounts).when(promotionRepository).findAll();

		List<PromotionDTO> PromotionDTOs = promotionService.findAllPromotions();

		Assertions.assertNotNull(PromotionDTOs, "Promotions should not be NULL");
		Assertions.assertTrue(!PromotionDTOs.isEmpty(), "Promotions should not be Empty");
		Assertions.assertTrue(PromotionDTOs.size()==2, "Promotions size not correct");
	}

	/**
	 * Test get promotion by code.
	 */
	@Test
	void testGetPromotionByCode() {
		Promotion promotion1 = new Promotion(1l, "Promo1", "Promo1", "Promo1");
		doReturn(promotion1).when(promotionRepository).findByPromoCodeIgnoreCase(Mockito.anyString());

		PromotionDTO returnedPromo = promotionService.getPromotionByCode(Mockito.anyString());

		Assertions.assertNotNull(returnedPromo, "Promotion should not be NULL");
		Assertions.assertTrue(returnedPromo.getPromoCode().equals("Promo1"), "Promotion is not correct");
	}

	/**
	 * Test create promotion.
	 */
	@Test
	void testCreatePromotion() {
		Promotion promotion1 = new Promotion(1l, "Promo1", "Promo1", "Promo1");
		doReturn(promotion1).when(promotionRepository).save(Mockito.any());

		PromotionDTO returnedPromo = promotionService.savePromotion(
				new PromotionDTO(1l, "Promo1", "Promo1", "Promo1"));

		Assertions.assertNotNull(returnedPromo, "Discount should not be NULL");
	}

}
