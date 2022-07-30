package ae.gov.dda.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private static final long serialVersionUID = 1900109148579424082L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;

	private String name;

	private String description;

	private String author;

	@ManyToOne
	@JoinColumn(name = "BOOK_CLASSIFICATION_id")
	private BookClassification bookClassification;

	@Column(precision = 10, scale = 2)
	private Double price;

	private String isbn;
}
