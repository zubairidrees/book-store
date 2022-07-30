package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.dto.DiscountDTO;
import ae.gov.dda.bookstore.dto.ShoppingCartDTO;
import ae.gov.dda.bookstore.exception.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.doReturn;


/**
 * The Class CheckoutServiceTest.
 *
 * @author Zubair.Idrees
 */

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CheckoutServiceTest {

	/** The checkout service. */
	@Autowired
	private CheckoutService checkoutService;

    /** The book service. */
    @MockBean
	private BookService bookService;

    /** The discount service. */
    @MockBean
	private DiscountService discountService;

	/**
	 * Test checkout card.
	 */
	@Test
	void testCheckoutCard() {
		BookDTO book1 = new BookDTO(3l, "Book 3", "Author 3", "ISBN-3", 100.00, "Book 3 Description", 3l);
        doReturn(book1).when(bookService).getBookById(Mockito.anyLong());

        DiscountDTO discount1 = new DiscountDTO(1l, 25, 1L, 1L);
        doReturn(discount1).when(discountService).getDiscountBybookClassificationAndPromoCode(
        		Mockito.anyLong(), Mockito.anyString());

        ArrayList<Long> bookIDs = new ArrayList<Long>(Arrays. asList(1l));
		Double totalPayable = checkoutService.checkout(
				new ShoppingCartDTO(bookIDs, "PROMO1"));

		Assertions.assertNotNull(totalPayable, "result should not be NULL");
		Assertions.assertTrue(totalPayable == 75.0d, "Total Payable is correct");
	}

	/**
	 * Test checkout card exception.
	 */
	@Test
	void testCheckoutCard_Exception() {
        doReturn(null).when(bookService).getBookById(Mockito.anyLong());

        ArrayList<Long> bookIDs = new ArrayList<Long>(Arrays. asList(1l));

	    RuntimeException rtException = Assertions.assertThrows(BookNotFoundException.class, () -> {
			checkoutService.checkout(new ShoppingCartDTO(bookIDs, "PROMO1"));
	    });

	    Assertions.assertEquals("Book [id=1] not found.", rtException.getMessage());
	}
}
