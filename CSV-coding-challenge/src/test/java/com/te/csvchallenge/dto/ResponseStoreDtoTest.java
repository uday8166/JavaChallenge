package com.te.csvchallenge.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ResponseStoreDtoTest {
	
	String json = "{\"storeId\":\"12\",\"postalCode\":\"563322\",\"city\":\"bangalore\",\"address\":\"krt\",\"openingDate\":\"12/09/2000\"}";
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void test() throws JsonProcessingException{
			ResponseStoreDto store = new ResponseStoreDto();
			store.setAddress("krt");
			store.setCity("bangalore");
			store.setOpeningDate(new Date());
			store.setPostalCode("563322");
			store.setStoreId("12");

//			System.out.println(mapper.writeValueAsString(store));

			ResponseStoreDto readValue = mapper.readValue(json, ResponseStoreDto.class);

			assertEquals(json, mapper.writeValueAsString(readValue));	
		
	}

}
