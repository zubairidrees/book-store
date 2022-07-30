package ae.gov.dda.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookClassification implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookClassificationId;

	private String code;

	private String displayName;

	@OneToMany(mappedBy = "bookClassification")
	private Set<Discount> discounts;

	@OneToMany(mappedBy = "bookClassification")
	private Set<Book> books;

}
