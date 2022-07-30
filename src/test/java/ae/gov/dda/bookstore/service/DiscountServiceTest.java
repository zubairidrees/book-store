package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.DiscountDTO;
import ae.gov.dda.bookstore.model.BookClassification;
import ae.gov.dda.bookstore.model.Discount;
import ae.gov.dda.bookstore.model.Promotion;
import ae.gov.dda.bookstore.repository.DiscountRepository;
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
 * The Class DiscountServiceTest.
 *
 * @author Zubair.Idrees
 */

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class DiscountServiceTest {

	/** The discount service. */
	@Autowired
	private DiscountService discountService;

	/** The discount repository. */
	@MockBean
	private DiscountRepository discountRepository;

	/** The promotion repository. */
	@MockBean
	private PromotionRepository promotionRepository;

	/**
	 * Test get all discounts.
	 */
	@Test
	void testGetAllDiscounts() {
		BookClassification bookClassification1 = new BookClassification();
		bookClassification1.setBookClassificationId(1L);

		Promotion promotion1 = new Promotion();
		promotion1.setPromoId(1L);
		Discount discount1 = new Discount(1l, 25, bookClassification1, promotion1);

		BookClassification bookClassification2 = new BookClassification();
		bookClassification2.setBookClassificationId(1L);
		Promotion promotion2= new Promotion();
		promotion2.setPromoId(2L);
		Discount discount2 = new Discount(2l, 50, bookClassification2, promotion2);
		List<Discount> discounts = new ArrayList<Discount>(Arrays.asList(discount1, discount2));

		doReturn(discounts).when(discountRepository).findAll();

		List<DiscountDTO> discountDTOs = discountService.getAllDiscounts();

		Assertions.assertNotNull(discountDTOs, "Discounts should not be NULL");
		Assertions.assertTrue(!discountDTOs.isEmpty(), "Discounts should not be Empty");
		Assertions.assertTrue(discountDTOs.size() == 2, "Discounts size not correct");
	}

	/**
	 * Test create discount.
	 */
	@Test
	void testCreateDiscount() {
		Promotion promotion1 = new Promotion(1l, "Promo1", "Promo1", "Promo1");
		doReturn(promotion1).when(promotionRepository).findByPromoId(Mockito.anyLong());

		BookClassification bookClassification1 = new BookClassification();
		bookClassification1.setBookClassificationId(1L);

		Discount discount1 = new Discount(1l, 25, bookClassification1, promotion1);
		doReturn(discount1).when(discountRepository).save(Mockito.any());

		DiscountDTO returnDiscount = discountService.saveDiscount(new DiscountDTO(1l, 25, 1L, 1L));

		Assertions.assertNotNull(returnDiscount, "Discount should not be NULL");
	}

	/**
	 * Test get discount by Book Classification and promo code.
	 */
	@Test
	void testGetDiscountBybookClassificationAndPromoCode() {
		BookClassification bookClassification1 = new BookClassification();
		bookClassification1.setBookClassificationId(1L);

		Promotion promotion1 = new Promotion();
		promotion1.setPromoId(1L);
		Discount discount1 = new Discount(1l, 25, bookClassification1, promotion1);
		doReturn(discount1).when(discountRepository).findBybookClassificationAndPromoCode(Mockito.anyLong(), Mockito.anyString());

		DiscountDTO returnDiscount = discountService.getDiscountBybookClassificationAndPromoCode(Mockito.anyLong(),
				Mockito.anyString());

		Assertions.assertNotNull(returnDiscount, "Discount should not be NULL");
	}

	/**
	 * Test get discount by Book Classification and promo id.
	 */
	@Test
	void testGetDiscountBybookClassificationAndPromoId() {
		BookClassification bookClassification1 = new BookClassification();
		bookClassification1.setBookClassificationId(1L);

		Promotion promotion1 = new Promotion();
		promotion1.setPromoId(1L);
		Discount discount1 = new Discount(1l, 25, bookClassification1, promotion1);
		doReturn(discount1).when(discountRepository).findBybookClassificationAndPromoId(Mockito.anyLong(), Mockito.anyLong());

		DiscountDTO returnDiscount = discountService.getDiscountBybookClassificationAndPromoId(Mockito.anyLong(),
				Mockito.anyLong());

		Assertions.assertNotNull(returnDiscount, "Discount should not be NULL");
	}

}
