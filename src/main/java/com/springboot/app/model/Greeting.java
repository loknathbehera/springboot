package com.springboot.app.model;

import java.math.BigDecimal;

public class Greeting {
	private BigDecimal id;
	private String text;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
