package com.te.csvchallenge.responsemessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
	private boolean error;
	private String message;
	private Object Data;

}
