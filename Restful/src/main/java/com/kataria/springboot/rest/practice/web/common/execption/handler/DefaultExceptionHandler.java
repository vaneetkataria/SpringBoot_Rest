package com.kataria.springboot.rest.practice.web.common.execption.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.kataria.springboot.rest.practice.core.beans.RestResponse;
import com.kataria.springboot.rest.practice.core.exception.CoreException;

@ControllerAdvice()
@RestController
public class DefaultExceptionHandler {

	@ExceptionHandler(CoreException.class)
	public ResponseEntity<RestResponse> handleCoreException(CoreException e, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(
				StringUtils.hasLength(e.getMessage()) ? e.getMessage() : HttpStatus.INTERNAL_SERVER_ERROR.name())
				.setPath(request.getRequestURI());
		ResponseEntity<RestResponse> finalResponse = new ResponseEntity<>(restResponse,
				null != e.getHttpStatus() ? e.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
		return finalResponse;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public RestResponse handleException(Exception e, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(
				StringUtils.hasLength(e.getMessage()) ? e.getMessage() : HttpStatus.INTERNAL_SERVER_ERROR.name())
				.setPath(request.getRequestURI());
		return restResponse;
	}

}
