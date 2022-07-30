package ae.gov.dda.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO implements Serializable {

	private static final long serialVersionUID = -310696326093732965L;
	@NotBlank(message = "Book Ids can't be empty")
	private List<Long> bookIDs;
	private String promoCode;


}