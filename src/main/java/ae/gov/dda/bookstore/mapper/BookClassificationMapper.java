package ae.gov.dda.bookstore.mapper;

import ae.gov.dda.bookstore.dto.BookClassificationDTO;
import ae.gov.dda.bookstore.model.BookClassification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * The Class bookClassificationMapper.
 * 
 * @author Zubair.Idrees
 */
@Mapper
public interface BookClassificationMapper {
	BookClassificationMapper INSTANCE = Mappers.getMapper(BookClassificationMapper.class);

	BookClassification convertToEntity(BookClassificationDTO bookClassificationDTO);
	BookClassificationDTO convertToDto(BookClassification bookClassification);
	List<BookClassificationDTO> convertbookClassifications(List<BookClassification> bookClassifications);

}
