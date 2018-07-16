package com.kataria.springboot.rest.practice.web.common.execption.handler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.kataria.springboot.rest.practice.core.beans.RestResponse;
import com.kataria.springboot.rest.practice.core.exception.CoreException;

@ControllerAdvice
@RestController
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CoreException.class)
	public ResponseEntity<RestResponse> handleCoreException(CoreException e, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(
				StringUtils.hasLength(e.getMessage()) ? e.getMessage() : HttpStatus.INTERNAL_SERVER_ERROR.name())
				.setPath(request.getRequestURI());
		return ResponseEntity.status(null != e.getHttpStatus() ? e.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR)
				.body(restResponse);
	}

	// @RequestParam and @PathVariables are not validated with javax.validations
	// @Valid annotation and other validations .
	// But annotating controller with @Validated of Spring annotation will make this
	// to work and ConstraintViolationException will be thrown. Which can be
	// gracefully handled here.
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public RestResponse handleCoreException(ConstraintViolationException e, HttpServletRequest request) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : HttpStatus.BAD_REQUEST.name())
				.setPath(request.getRequestURI());
		return restResponse;
	}

	// Any exception handled by ResponseEntityExceptionHandler will now be dealt
	// like below defined.
	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(ex.getMessage()).setPath(request.getDescription(false));
		return ResponseEntity.status(status).headers(headers).body(restResponse);
	}

	// Overriding this method specially as separate handling for extracting text
	// defined in Validation annotations is required.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		RestResponse restResponse = new RestResponse();
		String message = extractMessage(e.getBindingResult(), e);
		restResponse.setMessage(StringUtils.hasLength(message) ? message : "Invalid Input Params.")
				.setPath(request.getDescription(false));
		return ResponseEntity.badRequest().body(restResponse);
	}

	private String extractMessage(BindingResult bindingResult, Exception e) {
		if (null == bindingResult || null == bindingResult.getFieldErrors() || bindingResult.getFieldErrors().isEmpty())
			return e.getMessage();
		String message = "";
		for (FieldError error : bindingResult.getFieldErrors()) {
			if (!StringUtils.isEmpty(error.getDefaultMessage()))
				message = String.join(",", message, error.getDefaultMessage());
		}
		return !StringUtils.isEmpty(message) ? message.substring(1, message.length()) : message;

	}

}
