package com.te.csvchallenge.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.te.csvchallenge.dto.ResponseStoreDto;

@Service
public class StoreServiceImplementation implements StoreService {

	@Override
	public ResponseStoreDto fetchByStoreId(String storeId) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stores.csv"))) {
			@SuppressWarnings("resource")
			CSVParser parser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> csvRecord = parser.getRecords();
			for (CSVRecord csvRecordTwo : csvRecord) {
				if (csvRecordTwo.get("Store Id").equals(storeId)) {
					ResponseStoreDto storeDto = new ResponseStoreDto();
					storeDto.setStoreId(csvRecordTwo.get("Store Id"));
					storeDto.setPostalCode(csvRecordTwo.get("Post Code"));
					storeDto.setCity(csvRecordTwo.get("City"));
					storeDto.setAddress(csvRecordTwo.get("Address"));
					System.out.println();
					storeDto.setOpeningDate(new SimpleDateFormat("dd/MM/yyyy").parse(csvRecordTwo.get("opened Date")));
					return storeDto;
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();

		}
		return null;

	}

	@Override
	public List<ResponseStoreDto> fetchAllStores(String condition) {

		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stores.csv"))) {
			@SuppressWarnings("resource")
			CSVParser parser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			List<ResponseStoreDto> list = new ArrayList<>();
			Iterable<CSVRecord> csvRecord = parser.getRecords();

			for (CSVRecord csvRecordTwo : csvRecord) {
				ResponseStoreDto storeDto = new ResponseStoreDto();
				storeDto.setStoreId(csvRecordTwo.get("Store Id"));
				storeDto.setPostalCode(csvRecordTwo.get("Post Code"));
				storeDto.setCity(csvRecordTwo.get("City"));
				storeDto.setAddress(csvRecordTwo.get("Address"));
				storeDto.setOpeningDate(new SimpleDateFormat("dd/MM/yyyy").parse(csvRecordTwo.get("opened Date")));
				list.add(storeDto);
			}
			if (condition.equalsIgnoreCase("city")) {
				return list.stream().sorted(Comparator.comparing(ResponseStoreDto::getCity))
						.collect(Collectors.toList());
			} else if (condition.equalsIgnoreCase("date")) {
				return list.stream().sorted(Comparator.comparing(ResponseStoreDto::getOpeningDate))
						.collect(Collectors.toList());
			}
		} catch (Exception exception) {
			exception.printStackTrace();

		}
		return null;

	}

}
