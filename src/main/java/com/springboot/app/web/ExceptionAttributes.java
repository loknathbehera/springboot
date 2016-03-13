package com.springboot.app.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public interface ExceptionAttributes {
	Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpServletRequest,
			HttpStatus httpStatus);
}
