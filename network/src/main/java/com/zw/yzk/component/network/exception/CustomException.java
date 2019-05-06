package com.zw.yzk.component.network.exception;

public class CustomException extends RuntimeException {

    public static final int UNKNOWN_ERROR             = -1;//未知错误 `
    public static final int UNAUTHORIZED              = 401;//http返回码
    public static final int FORBIDDEN                 = 403;//http返回码
    public static final int NOT_FOUND                 = 404;//http返回码
    public static final int REQUEST_TIMEOUT           = 408;//http返回码
    public static final int INTERNAL_SERVER_ERROR     = 500;//http返回码
    public static final int BAD_GATEWAY               = 502;//http返回码
    public static final int SERVICE_UNAVAILABLE       = 503;//http返回码
    public static final int GATEWAY_TIMEOUT           = 504;//http返回码
    public static final int UNKNOWN_INVALID_TOKEN     = 444;//用户凭证失效
    public static final int MUST_LOGIN                = 445;//登陆用户才有权操作
    public static final int PARSE_ERROR               = 1000;//解析服务器数据失败
    public static final int UNKNOWN_HOST              = 1001;//UnknownHostException
    public static final int SOCKET_ERROR              = 1002;//SocketException
    public static final int SOCKET_TIMEOUT            = 1003;//SocketTimeoutException
    public static final int IO_ERROR                  = 10004;//IOException
    public static final int CONNECT_ERROR             = 10005;//ConnectException
    public static final int SSLHANDSHAKE_ERROR        = 10006;//SSLHandshakeException
    public static final int NETWORK_ERROR             = 1007;//网络错误
    public static final int FILE_CACHE_ERROR          = 1008;//文件保存出错

    private int errCode;
    private String errMsg;

    public CustomException() {
    }

    public CustomException(int code) {
        errCode = code;
    }

    public CustomException(String errMsg) {
        this.errMsg = errMsg;
    }

    public CustomException(int code, String errMsg) {
        this.errCode = code;
        this.errMsg = errMsg;
    }

    public void setErrMsg(String msg) {
        this.errMsg = msg;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setError(int code, String msg) {
        this.errCode = code;
        this.errMsg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
