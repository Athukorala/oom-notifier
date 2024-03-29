
package com.swlc.bolton.notifier.json;

import java.io.Serializable;

/**
 *
 * @author athukorala
 */
public class CommonResponse implements Serializable {

    private boolean success;
    private String message;
    private Object body;

    public CommonResponse() {
    }

    public CommonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CommonResponse(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public CommonResponse(boolean success, Object body) {
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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonResponse{" + "success=" + success + ", message=" + message + ", body=" + body + '}';
    }

}
