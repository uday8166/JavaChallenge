package com.te.csvchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.csvchallenge.dto.ResponseStoreDto;
import com.te.csvchallenge.responsemessage.ResponseMessage;
import com.te.csvchallenge.service.StoreServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class StoreController {
	@Autowired
	private StoreServiceImplementation implementation;

	@GetMapping("/fetch/{storeId}")
	public ResponseEntity<ResponseMessage> fetchStoreDetailsByID(@PathVariable String storeId) {
		ResponseStoreDto responseStoreDto = implementation.fetchByStoreId(storeId);
		if (responseStoreDto != null) {
			log.info("Displaying the Store Details {} " , responseStoreDto);
			return new ResponseEntity<>(new ResponseMessage(false, "Displaying the Store Details", responseStoreDto),
					HttpStatus.OK);
		} else {
			log.error("No Store Details found");
			return new ResponseEntity<>(new ResponseMessage(true, "No Store Details found  ", null),
					HttpStatus.NOT_FOUND);  
		}
	}

	@GetMapping("/fetchall/{condition}")
	public ResponseEntity<ResponseMessage> fetchAllStoreDetails(@PathVariable String condition) {
		List<ResponseStoreDto> fetchAllStores = implementation.fetchAllStores(condition);
		if (fetchAllStores != null) {
			log.info("Displaying All the Stores Details {} " ,fetchAllStores);
			return new ResponseEntity<>(new ResponseMessage(false, "Displaying All the Stores Details", fetchAllStores),
					HttpStatus.OK);

		} else {
			log.error("No Store Details found");
			return new ResponseEntity<>(new ResponseMessage(true, "No Store Details found  ", null),
					HttpStatus.NOT_FOUND); 
		}
	}
}
