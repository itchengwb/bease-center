package com.noriental.module.sms.model;


import java.io.Serializable;

public class CommonDes implements Serializable {
    private int code;
    private String message = "success";

    public CommonDes() {
    }

    public boolean success() {
        return this.code == 0;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(CommonDes failure) {
        this.code = failure.getCode() != 0 ? failure.getCode() : -1;
        if (this.code != -1) {
            this.message = failure.getMessage();
        } else {
            //this.message = BasicErrorCode.errorCodeFor(this.code).getComment();
        }

    }

  /*  public String toString() {
        return JsonUtil.obj2Json(this);
    }*/

    public static CommonDes getBaseResponse(RuntimeException exception) {
        CommonDes exceptionDes = new CommonDes();
        Object baseException = null;
        /*if (exception instanceof BaseException) {
            baseException = (BaseException)exception;
        } else {
            baseException = new UnknownException(exception);
        }

        exceptionDes.setCode(((BaseException)baseException).getErrorCodeValue());
        exceptionDes.setMessage(((BaseException)baseException).getMessage());*/
        return exceptionDes;
    }

    public static CommonDes makeErrorResult() {
        return new CommonDes();
    }
}
