package com.lk.communicate.server.util;

public class DecoderException extends Exception {
	private static final long serialVersionUID = 1L;

	public DecoderException() {
		super();
	}

	public DecoderException(String message) {
		super(message);
	}

	public DecoderException(String message, Throwable cause) {
		super(message, cause);
	}

	public DecoderException(Throwable cause) {
		super(cause);
	}
}
