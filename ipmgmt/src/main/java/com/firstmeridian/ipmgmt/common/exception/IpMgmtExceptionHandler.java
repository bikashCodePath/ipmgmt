package com.firstmeridian.ipmgmt.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IpMgmtExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessErrorResponse> handleBusinessException(BusinessException exception){
		BusinessErrorResponse errorResponse = new BusinessErrorResponse();
		errorResponse.setErrorCode(exception.getLocalizedMessage());
		errorResponse.setErrorMessage(exception.getErrorDescription());
		return new ResponseEntity<BusinessErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
