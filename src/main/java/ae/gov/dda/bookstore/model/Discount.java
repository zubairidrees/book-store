package ae.gov.dda.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Discount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long discountId;

	private Integer discountPercentage;

	@ManyToOne
	@JoinColumn(name = "BOOK_CLASSIFICATION_id")
	private BookClassification bookClassification;

	@ManyToOne
	@JoinColumn(name = "promo_id")
	private Promotion promotion;


}
