package ae.gov.dda.bookstore.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class DiscountDTO implements Serializable {

	private Long discountId;

	@Min(1)
	@Max(75)
	@NonNull
	private Integer discountPercentage;

	@NonNull
	private Long bookClassificationId;

	@NonNull
	private Long promoId;

}
