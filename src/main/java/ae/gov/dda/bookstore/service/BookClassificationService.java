package ae.gov.dda.bookstore.service;

import ae.gov.dda.bookstore.dto.BookClassificationDTO;

import java.util.List;


/**
 * The Interface bookClassificationService.
 * 
 * @author Zubair.Idrees
 */
public interface BookClassificationService {

	/**
	 * Find all Book Classifications.
	 *
	 * @return the list
	 */
	List<BookClassificationDTO> findAllbookClassifications();

}
