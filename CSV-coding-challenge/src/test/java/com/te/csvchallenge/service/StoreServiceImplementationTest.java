package com.te.csvchallenge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.opencsv.CSVReader;
import com.te.csvchallenge.dto.ResponseStoreDto;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StoreServiceImplementationTest {
	
	CSVReader reader;
	
	@InjectMocks
	private StoreServiceImplementation service;

	public StoreServiceImplementationTest() {
		reader = mock(CSVReader.class); 
	}
	

	@Test
	void testFetchByStoreId() throws Exception {
		
		ResponseStoreDto store = new ResponseStoreDto();
		store.setAddress("krt");
		store.setCity("bangalore");
		store.setPostalCode("563322");
		store.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");

		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", service.fetchByStoreId(store.getStoreId()).getStoreId());
	
		
	}

	@Test
	void testFetchAllStores() {

		ResponseStoreDto store = new ResponseStoreDto();
		store.setAddress("krt");
		store.setCity("bangalore");
		store.setPostalCode("563322");
		store.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");

		List<ResponseStoreDto> list = new ArrayList<>();
		list.add(store);

		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", service.fetchAllStores("city").get(0).getStoreId());
	}

}
