package idv.jeff.offer.mgmt.Controller;

import idv.jeff.offer.mgmt.Model.Currency;
import idv.jeff.offer.mgmt.Model.Customer;
import idv.jeff.offer.mgmt.Model.Offer;
import idv.jeff.offer.mgmt.Model.OfferStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
@ContextConfiguration(classes = OfferControllerTest.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OfferController offerController;

    Offer mockOffer = new Offer();
    Customer mockCustomer = new Customer();
    Date mockDueDate = new Date();

    @Before
    public void setUp() {
        mockCustomer.setId(1L);
        mockCustomer.setName("mockCustomer");
        mockCustomer.setAddress("London");
        mockCustomer.setPhoneNumber("012345678");

        mockOffer.setId(1L);
        mockOffer.setQuantity(1);
        mockOffer.setPrice(1);
        mockOffer.setCurrency(Currency.GBP);
        mockOffer.setRemark("mockOffer");
        mockOffer.setOfferStatus(OfferStatus.Created);
        mockOffer.setDueDate(mockDueDate);
        mockOffer.setCustomer(mockCustomer);

    }

    @Test
    public void getAllOffers() throws Exception {
        //given
        List<Offer> foundAllOfferList = Arrays.asList(mockOffer);
        given(offerController.getAllOffers()).willReturn(foundAllOfferList);

        //when && then
        mvc.perform(get("/api/offers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].quantity", is(mockOffer.getQuantity())))
                .andExpect(jsonPath("$[0].price", is(mockOffer.getPrice())))
                .andExpect(jsonPath("$[0].currency", is(mockOffer.getCurrency().toString())))
                .andExpect(jsonPath("$[0].remark", is(mockOffer.getRemark())))
                .andExpect(jsonPath("$[0].offerStatus", is(mockOffer.getOfferStatus().toString())));
    }

    @Test
    public void createOffer() throws Exception {
        given(offerController.createOffer(any(Offer.class))).willReturn(mockOffer);

        mvc.perform(post("/api/offers").contentType("application/json;charset=UTF-8")
                .content(createMockOfferInfo(1, 1, Currency.GBP, OfferStatus.Created, "mockOffer")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.price").value(1))
                .andExpect(jsonPath("$.currency", is(mockOffer.getCurrency().toString())))
                .andExpect(jsonPath("$.offerStatus").value(mockOffer.getOfferStatus().toString()))
                .andExpect(jsonPath("$.remark").value("mockOffer"));

    }

    @Test
    public void getOfferById() throws Exception {
        given(offerController.getOfferById(mockOffer.getId())).willReturn(mockOffer);
        mvc.perform(get("/api/offers/" + mockOffer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(mockOffer.getQuantity()))
                .andExpect(jsonPath("$.price").value(mockOffer.getPrice()))
                .andExpect(jsonPath("$.currency", is(mockOffer.getCurrency().toString())))
                .andExpect(jsonPath("$.offerStatus").value(mockOffer.getOfferStatus().toString()))
                .andExpect(jsonPath("$.remark").value(mockOffer.getRemark()));
    }

    @Test
    public void updateOffer() throws Exception {
        given(offerController.updateOffer(eq(mockOffer.getId()), any(Offer.class))).willReturn(mockOffer);
        mvc.perform(put("/api/offers/" + mockOffer.getId()).contentType("application/json;charset=UTF-8")
                .content(createMockOfferInfo(2, 2, Currency.GBP, OfferStatus.Delivered, "updated mockOffer")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(mockOffer.getQuantity()))
                .andExpect(jsonPath("$.price").value(mockOffer.getPrice()))
                .andExpect(jsonPath("$.currency", is(mockOffer.getCurrency().toString())))
                .andExpect(jsonPath("$.offerStatus").value(mockOffer.getOfferStatus().toString()))
                .andExpect(jsonPath("$.remark").value(mockOffer.getRemark()));

    }

    @Test
    public void deleteOffer() throws Exception {
        mvc.perform(delete("/api/offers/" + mockOffer.getId()))
                .andExpect(status().isOk());
    }

    private static String createMockOfferInfo (int quantity, int price, Currency currency, OfferStatus offerStatus, String remark) {
        return "{"
                + "\"quantity\":\"" + quantity + "\","
                + "\"price\":\"" + price + "\","
                + "\"currency\":\"" + currency + "\","
                + "\"offerStatus\":\"" + offerStatus + "\","
                + "\"remark\":\"" + remark + "\"}";
    }
}