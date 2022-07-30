package ae.gov.dda.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


/**
 * The Class BookstoreApplicationTests.
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ApplicationTests {

	/**
	 * Context loads.
	 */
	@Test
	void contextLoads() {
	}

}
