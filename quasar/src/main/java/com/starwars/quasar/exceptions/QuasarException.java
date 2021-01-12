package com.starwars.quasar.exceptions;

@SuppressWarnings("serial")
public class QuasarException extends RuntimeException {

	public QuasarException(String msg) {
		super(msg);
	}
}