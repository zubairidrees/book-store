package ae.gov.dda.bookstore.controller;

import ae.gov.dda.bookstore.dto.ShoppingCartDTO;
import ae.gov.dda.bookstore.service.CheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The Class CheckoutControllerTest.
 *
 * @author Zubair.Idrees
 */
@WebMvcTest(CheckoutController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class CheckoutControllerTest {

    /** The mock mvc. */
    @Autowired
    private MockMvc mockMvc;

    /** The mapper. */
    @Autowired
    private ObjectMapper mapper;

    /** The checkout service. */
    @MockBean
    private CheckoutService checkoutService;

	/**
	 * Test checkout cart.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testCheckoutCart() throws Exception {
		ArrayList<Long> bookIDs = new ArrayList<Long>(Arrays. asList(1l, 2l, 3l));
		ShoppingCartDTO cartVO = new ShoppingCartDTO(bookIDs, "SUMMER22");
	    Mockito.when(checkoutService.checkout(Mockito.any())).thenReturn(anyDouble());

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/checkout/")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(cartVO));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isAccepted())
		        .andExpect(jsonPath("$", notNullValue()));
	}

}
