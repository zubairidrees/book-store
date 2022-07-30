package ae.gov.dda.bookstore.repository;

import ae.gov.dda.bookstore.model.BookClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface bookClassificationRepository.
 * 
 * @author Zubair.Idrees
 */
@Repository
public interface BookClassificationRepository extends JpaRepository<BookClassification, Long> {

}
