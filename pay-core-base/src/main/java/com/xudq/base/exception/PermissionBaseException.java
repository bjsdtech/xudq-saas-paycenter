package com.xudq.base.exception;

import org.springframework.http.HttpStatus;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class PermissionBaseException extends RuntimeException {

	private static final long serialVersionUID = -1302000576642490338L;

	private HttpStatus status;

    private String description = "";

    public PermissionBaseException(HttpStatus status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}
