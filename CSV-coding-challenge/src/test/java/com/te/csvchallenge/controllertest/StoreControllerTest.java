package com.te.csvchallenge.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.csvchallenge.controller.StoreController;
import com.te.csvchallenge.dto.ResponseStoreDto;
import com.te.csvchallenge.responsemessage.ResponseMessage;
import com.te.csvchallenge.service.StoreService;
import com.te.csvchallenge.service.StoreServiceImplementation;

@SpringBootTest
class StoreControllerTest {

	@Mock
	private StoreService implementation;

	@InjectMocks
	private StoreController storeController;

	@Autowired
	private WebApplicationContext context;

	@SuppressWarnings("unused")
	private MockMvc mockMvc;
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		this.mapper = new ObjectMapper();
	}

	@Test
	void testFetchStoreDetailsByID() throws UnsupportedEncodingException, Exception {

		ResponseStoreDto store = new ResponseStoreDto();
		store.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");
		store.setPostalCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");
		store.setOpeningDate(new Date());

		Mockito.when(implementation.fetchByStoreId(Mockito.anyString())).thenReturn(store);
		String jsonObject = mapper.writeValueAsString(store);
		String result = mockMvc
				.perform(get("/api/v1/fetch/1525eec4-7bed-4597-bf5a-e06fcede5f4f")
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonObject))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseMessage responseMessage = mapper.readValue(result, ResponseMessage.class);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) responseMessage.getData();
		for (Map.Entry<String, String> m : map.entrySet()) {
			assertEquals(store.getStoreId(), m.getValue());
			break;
		}
	}

	
	
	@Test
	void getStoresByCityTest() throws UnsupportedEncodingException, Exception {

		List<ResponseStoreDto> list = new ArrayList();
		ResponseStoreDto store = new ResponseStoreDto();
		store.setStoreId("12");
		store.setPostalCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");
		store.setOpeningDate(new Date());
		list.add(store);

		when(implementation.fetchAllStores("city")).thenReturn(list);

		String contentAsString = mockMvc
				.perform(get("/api/v1/fetchall/city"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
 ResponseMessage readValue = mapper.readValue(contentAsString, ResponseMessage.class);
		assertEquals("Displaying All the Stores Details", readValue.getMessage());
	}
	

}
