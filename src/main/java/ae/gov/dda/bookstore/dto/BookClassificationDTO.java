package ae.gov.dda.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookClassificationDTO implements Serializable {

	private Long bookClassificationId;
	private String code;
	private String displayName;

}
