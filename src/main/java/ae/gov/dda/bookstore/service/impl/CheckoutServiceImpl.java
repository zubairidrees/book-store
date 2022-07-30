package ae.gov.dda.bookstore.service.impl;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.dto.DiscountDTO;
import ae.gov.dda.bookstore.dto.ShoppingCartDTO;
import ae.gov.dda.bookstore.exception.BookNotFoundException;
import ae.gov.dda.bookstore.service.BookService;
import ae.gov.dda.bookstore.service.CheckoutService;
import ae.gov.dda.bookstore.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class Checkout Service Implementation.
 * 
 * @author Zubair.Idrees
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

	/** The logger. */
	static Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

	/** The book service. */
	@Autowired
	private BookService bookService;

	/** The discount service. */
	@Autowired
	private DiscountService discountService;

	/**
	 * Checkout.
	 *
	 * @param shoppingCartDTO the shopping cart DTO
	 * @return the shopping cart DTO
	 */
	@Override
	public Double checkout(ShoppingCartDTO shoppingCartDTO) {
		Double totalPayable = 0.0d;
		List<Long> bookIDs = shoppingCartDTO.getBookIDs();
		for (Long bookId : bookIDs) {
			BookDTO book = bookService.getBookById(bookId);
			if (book != null) {
				Double price = book.getPrice();
				String promoCode = shoppingCartDTO.getPromoCode();

				// check if promotion code is present
				price = getDiscountedPrice(book, price, promoCode);
				totalPayable += price;
			} else {
				throw new BookNotFoundException(bookId);
			}
		}
		return totalPayable;
	}

	/**
	 * Gets the discounted price.
	 *
	 * @param book the book
	 * @param price the price
	 * @param promoCode the promo code
	 * @return the discounted price
	 */
	private Double getDiscountedPrice(BookDTO book, Double price, String promoCode) {
		if (promoCode != null && !promoCode.isEmpty()) {
			DiscountDTO discount = discountService.getDiscountBybookClassificationAndPromoCode(book.getBookClassificationId(), promoCode);
			price = discount != null ? applyDiscount(price, discount) : price;
		}
		return price;
	}

	/**
	 * Apply discount.
	 *
	 * @param price the price
	 * @param discount the discount
	 * @return the double
	 */
	private Double applyDiscount(Double price, DiscountDTO discount) {
		Double discountPercent = Double.valueOf(discount.getDiscountPercentage());
		if (discountPercent > 0) {
			// calculate discount mount
			Double discountAmount = ((discountPercent / 100) * price);
			// apply discount on book price
			price = price - discountAmount;
		}
		return price;
	}

}
