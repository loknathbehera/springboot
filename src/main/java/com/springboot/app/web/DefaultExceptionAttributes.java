package com.springboot.app.web;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class DefaultExceptionAttributes implements ExceptionAttributes {

	@Override
	public Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpServletRequest,
			HttpStatus httpStatus) {

		Map<String, Object> exceptionalAttributes = new LinkedHashMap<String, Object>();

		exceptionalAttributes.put("timeStamp", new Date());

		exceptionalAttributes.put("status", httpStatus.value());

		exceptionalAttributes.put("error", httpStatus.getReasonPhrase());

		exceptionalAttributes.put("exception", exception.getClass().getName());

		return exceptionalAttributes;
	}

}
