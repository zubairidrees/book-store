package ae.gov.dda.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO implements Serializable {
	private static final long serialVersionUID = -3377852153505771264L;
	private Long promoId;
    @NotBlank
	private String promoCode;
    private String name;
	private String description;

}
