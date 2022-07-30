package ae.gov.dda.bookstore.mapper;

import ae.gov.dda.bookstore.dto.PromotionDTO;
import ae.gov.dda.bookstore.model.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * The Class PromotionMapper.
 * 
 * @author Zubair.Idrees
 */
@Mapper
public interface PromotionMapper {

	PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

	Promotion convertToEntity(PromotionDTO promotionDTO);
	PromotionDTO convertToDto(Promotion promotion);
	List<PromotionDTO> convertPromotions(List<Promotion> promotions);
}
