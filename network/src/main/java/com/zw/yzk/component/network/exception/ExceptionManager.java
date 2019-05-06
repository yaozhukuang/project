package com.zw.yzk.component.network.exception;


public class ExceptionManager {

    private static ExceptionManager instance;
    private EHandler eHandler;


    private ExceptionManager() {
    }

    public static ExceptionManager getInstance() {
        if (instance == null) {
            synchronized (ExceptionManager.class) {
                if (instance == null) {
                    instance = new ExceptionManager();
                }
            }
        }
        return instance;
    }

    public EHandler getEHandler() {
        if (eHandler == null) {
            throw new IllegalStateException("EHandler must be set");
        }
        return eHandler;
    }

    public void setEHandler(EHandler eHandler) {
        if (eHandler == null) {
            throw new IllegalArgumentException("EHandler can not be null");
        }
        this.eHandler = eHandler;
    }
}
