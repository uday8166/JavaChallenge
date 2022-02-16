package com.te.csvchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CsvFileOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvFileOperationsApplication.class, args);
	}

}
