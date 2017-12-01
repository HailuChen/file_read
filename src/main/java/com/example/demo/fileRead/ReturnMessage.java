package com.example.demo.fileRead;


public class ReturnMessage {

    private int retCode;
    private String retMsg;
    private Object result;

    public ReturnMessage(int retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public ReturnMessage(int retCode, String retMsg, Object result) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.result = result;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
