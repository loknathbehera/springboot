package com.springboot.app.api;

import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.app.web.DefaultExceptionAttributes;
import com.springboot.app.web.ExceptionAttributes;

public class BaseController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Map<String, Object>> handleNoResultException(NoResultException exception,
			HttpServletRequest httpServletRequest) {

		ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

		logger.error("- NoResultException: ", exception.getMessage());

		Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, httpServletRequest,
				HttpStatus.NOT_FOUND);

		return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception exception,
			HttpServletRequest httpServletRequest) {

		ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

		logger.error(" Handled Exception: ", exception.getMessage());

		Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, httpServletRequest,
				HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
