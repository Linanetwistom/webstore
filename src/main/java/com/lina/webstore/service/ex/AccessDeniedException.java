package com.lina.webstore.service.ex;

public class AccessDeniedException extends ServiceException{

    public AccessDeniedException() {
    }

    public AccessDeniedException(String arg0) {
        super(arg0);
    }

    public AccessDeniedException(Throwable arg0) {
        super(arg0);
    }

    public AccessDeniedException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public AccessDeniedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
