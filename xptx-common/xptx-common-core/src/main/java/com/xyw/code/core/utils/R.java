package com.xyw.code.core.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 结果工具类
 * @Date: Create in 上午9:40 2019/10/14
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = 200;
    private String msg;
    private T data;

    public static <T> R<T> ok(){
        return ok(null);
    }

    public static <T> R<T> ok(T data){
        R<T> r = new R<>();
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(){
        return  error(500,"未知异常，请联系管理员");
    }

    public static <T> R<T> error(String msg){
        return error(500,msg);
    }

    public static <T> R<T> error(Integer code,String msg){
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(code);
        return r;
    }


    public boolean isSuccess() {
        if(code==200){
            return true;
        }else{
            return false;
        }
    }
}
