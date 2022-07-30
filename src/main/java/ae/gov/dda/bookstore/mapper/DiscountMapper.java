package ae.gov.dda.bookstore.mapper;

import ae.gov.dda.bookstore.dto.DiscountDTO;
import ae.gov.dda.bookstore.model.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * The Class DiscountMapper.
 * 
 * @author Zubair.Idrees
 */
@Mapper
public interface DiscountMapper {

	DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

	Discount convertToEntity(DiscountDTO discountDTO);
	DiscountDTO convertToDto(Discount discount);
	List<DiscountDTO> convertDiscounts(List<Discount> discount);

}
