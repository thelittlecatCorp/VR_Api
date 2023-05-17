package kr.dbas.psychologyvrapi.dao;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseHeader {
	
	private String code;
	private String message;
	
}
