package cn.scnu.com.util.Result;


public enum ResultCode {
    SUCCESS(200,"接口访问成功"),
    PARAM_IS_INVALID(201,"参数不正确"),
    NOT_FIND(504,"请求数据无法找到(不在数据库或缓存内)");



    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
