package ae.gov.dda.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Promotion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	private Long promoId;
	@NonNull
	private String promoCode;
	@NonNull
	private String name;
	@NonNull
	private String description;

	@OneToMany(mappedBy = "promotion")
	private Set<Discount> discounts;

}
