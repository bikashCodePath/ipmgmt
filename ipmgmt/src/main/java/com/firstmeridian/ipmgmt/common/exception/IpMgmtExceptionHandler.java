package com.firstmeridian.ipmgmt.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IpMgmtExceptionHandler {

	private static final String SERVER_ERROR = "SERVER_ERROR";
	private static final String SERVER_MESSAGE = "Ip pool manager is unable to process request !";

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessErrorResponse> handleBusinessException(BusinessException exception){
		BusinessErrorResponse errorResponse = new BusinessErrorResponse();
		errorResponse.setErrorCode(exception.getLocalizedMessage());
		errorResponse.setErrorMessage(exception.getErrorDescription());
		return new ResponseEntity<BusinessErrorResponse>(errorResponse, exception.getStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BusinessErrorResponse> handleAllException(Exception exception){
		BusinessErrorResponse errorResponse = new BusinessErrorResponse();
		errorResponse.setErrorCode(SERVER_ERROR);
		errorResponse.setErrorMessage(SERVER_MESSAGE);
		return new ResponseEntity<BusinessErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
