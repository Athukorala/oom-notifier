
package com.swlc.bolton.notifier.json;

import java.io.Serializable;

/**
 *
 * @author athukorala
 */
public class CommonResponse<T> implements Serializable {

    private boolean success;
    private String message;
    private T body;

    public CommonResponse() {
    }

    public CommonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CommonResponse(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public CommonResponse(boolean success, T body) {
        this.success = success;
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonResponse{" + "success=" + success + ", message=" + message + ", body=" + body + '}';
    }

}
