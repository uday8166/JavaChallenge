package com.te.csvchallenge.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStoreDto {
	private String storeId;
	private String postalCode;
	private String city;
	private String address;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date openingDate;

}
