package com.te.csvchallenge.storeservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.opencsv.CSVReader;
import com.te.csvchallenge.dto.ResponseStoreDto;
import com.te.csvchallenge.service.StoreServiceImplementation;

@SpringBootTest 
class StoreServiceTest {
	
	@InjectMocks
	private StoreServiceImplementation serv;  
	
	

	@Test
	void testFetchByStoreId() {
		
		ResponseStoreDto store = new ResponseStoreDto();
		store.setAddress("krt");
		store.setCity("bangalore");
		store.setOpeningDate(new Date());
		store.setPostalCode("563322");
		store.setStoreId("12");
		String[] abc = {"as","df"};
		
		CSVReader csvReader=null;
		try {
			csvReader = new CSVReader(new FileReader(Mockito.any(File.class)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			when(csvReader.readNext()).thenReturn(abc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		when(serv.fetchByStoreId(store.getStoreId())).thenReturn(store);
		
		assertEquals(store,serv.fetchByStoreId(store.getStoreId()));
		
	}

	@Test
	void testFetchAllStores() {
	    
		List<ResponseStoreDto>   store = (List<ResponseStoreDto>) new ResponseStoreDto();
		
		ResponseStoreDto store1=new ResponseStoreDto();
		store1.setAddress("krt");
		store1.setCity("bangalore");
		store1.setOpeningDate(new Date());
		store1.setPostalCode("563322");
		store1.setStoreId("12");
		
		store.set(0, store1);
		String[] abc = {"as","df"};
		
		CSVReader csvReader=null;
		try {
			csvReader = new CSVReader(new FileReader(Mockito.any(File.class)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			when(csvReader.readNext()).thenReturn(abc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		when(serv.fetchAllStores(store1.getStoreId())).thenReturn(store);
		
		assertEquals(store,serv.fetchByStoreId(store1.getStoreId()));
		
		
	}

}
