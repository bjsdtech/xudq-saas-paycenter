package com.xudq.base.exception;
/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class InvalidParaException extends RuntimeException {

	public InvalidParaException() {
		super();
	}

	public InvalidParaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidParaException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParaException(String message) {
		super(message);
	}

	public InvalidParaException(Throwable cause) {
		super(cause);
	}
}
