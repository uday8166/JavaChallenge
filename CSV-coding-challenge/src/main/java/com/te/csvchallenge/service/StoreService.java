package com.te.csvchallenge.service;

import java.util.List;

import com.te.csvchallenge.dto.ResponseStoreDto;

public interface StoreService {

	ResponseStoreDto fetchByStoreId(String storeId);

	List<ResponseStoreDto> fetchAllStores(String condition);

}
