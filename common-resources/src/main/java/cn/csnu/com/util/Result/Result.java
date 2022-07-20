package com.example.util.Result;

import lombok.Data;

import java.io.Serializable;


//返回类
@Data
public class Result implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();


    /**
     * 描述
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;
    /**
     * 结果对象
     */
    private Object data;


    //下面是相应的静态方法

    //请求成功返回，不需要返回数据
    //由于枚举类型化为json格式将会只有名字，很奇怪，因此必须拆开

    public static Result success(){
        Result result=new Result();

        result.success=true;
        result.code=ResultCode.SUCCESS.code();
        result.message=ResultCode.SUCCESS.message();

        return result;
    }

    //请求成功返回，需要返回数据,从参数返回
    public static Result success(Object data){
        Result result=new Result();

        result.success=true;
        result.code=ResultCode.SUCCESS.code();
        result.message=ResultCode.SUCCESS.message();
        result.data=data;

        return result;
    }
    //请求失败返回，返回的错误类型从参数输入
    public static Result error(ResultCode resultCode){
        Result result=new Result();

        result.success=false;
        result.code=resultCode.code();
        result.message=resultCode.message();

        return result;
    }
    //请求失败返回，返回的错误类型从参数输入，返回的数据从参数输入
    public static Result error(ResultCode resultCode,Object data){
        Result result=new Result();

        result.success=false;
        result.data=data;
        result.code=resultCode.code();
        result.message=resultCode.message();

        return result;
    }

    //请求失败返回，信息自定义
    public static Result error(String message){
        Result result=new Result();

        result.success=false;
        result.message=message;

        return result;
    }

}
