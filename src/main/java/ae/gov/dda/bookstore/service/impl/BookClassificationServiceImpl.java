package ae.gov.dda.bookstore.service.impl;

import ae.gov.dda.bookstore.dto.BookClassificationDTO;
import ae.gov.dda.bookstore.mapper.BookClassificationMapper;
import ae.gov.dda.bookstore.model.BookClassification;
import ae.gov.dda.bookstore.repository.BookClassificationRepository;
import ae.gov.dda.bookstore.service.BookClassificationService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The Class bookClassification Service Implementation.
 * 
 * @author Zubair.Idrees
 */
@Service
public class BookClassificationServiceImpl implements BookClassificationService {

	/** The Book Classification repository. */
	private final BookClassificationRepository bookClassificationRepository;


	/**
	 * Instantiates a new Book Classification service impl.
	 *
	 * @param bookClassificationRepository the Book Classification repository
	 */
	public BookClassificationServiceImpl(BookClassificationRepository bookClassificationRepository) {
		this.bookClassificationRepository = bookClassificationRepository;
	}

	/**
	 * Find all Book Classifications.
	 *
	 * @return the list
	 */
	@Override
	public List<BookClassificationDTO> findAllbookClassifications() {
		List<BookClassification> bookClassifications = bookClassificationRepository.findAll();

		return BookClassificationMapper.INSTANCE.convertbookClassifications(bookClassifications);
	}

}
