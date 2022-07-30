package ae.gov.dda.bookstore.mapper;

import ae.gov.dda.bookstore.dto.BookDTO;
import ae.gov.dda.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * The Interface BookMapper.
 * 
 * @author Zubair.Idrees
 */

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	@Mappings({
			@Mapping(target="bookId", source="bookDTO.bookId"),
			@Mapping(target="name", source="bookDTO.name"),
			@Mapping(target="description", source="bookDTO.description"),
			@Mapping(target="author", source="bookDTO.author"),
			@Mapping(target = "price", source = "bookDTO.price"),
			@Mapping(target = "isbn", source = "bookDTO.isbn"),
			@Mapping(target = "bookClassification.bookClassificationId", source = "bookDTO.bookClassificationId")
	})
	Book convertIntoEntity(BookDTO bookDTO);

	@Mappings({
			@Mapping(target="bookId", source="book.bookId"),
			@Mapping(target="name", source="book.name"),
			@Mapping(target="description", source="book.description"),
			@Mapping(target="author", source="book.author"),
			@Mapping(target = "price", source = "book.price"),
			@Mapping(target = "isbn", source = "book.isbn"),
			@Mapping(target = "bookClassificationId", source = "book.bookClassification.bookClassificationId")
	})
	BookDTO convertIntoDto(Book book);

	List<BookDTO> convertBooks(List<Book> books);
}
