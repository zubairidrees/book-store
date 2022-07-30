package ae.gov.dda.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

	private static final long serialVersionUID = 1900109148529424082L;
	private Long bookId;

	@NotBlank(message = "Name can't be empty")
	private String name;

	@NotBlank(message = "Author can't be empty")
	private String author;

	@NotBlank(message = "ISBN can't be empty")
	private String isbn;

	@NotNull(message = "Price can't be empty")
	private Double price;

	@NotBlank(message = "Description can't be empty")
	private String description;

	@NotNull(message = "Book Classification can't be empty")
	private Long bookClassificationId;

}
