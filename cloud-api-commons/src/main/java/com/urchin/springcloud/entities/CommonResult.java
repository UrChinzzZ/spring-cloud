package com.urchin.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public   class CommonResult<T> {

    private Integer code;
    private  String message;
    private  T data;

    public  CommonResult(Integer code,String message){
        this(code,message,null);
    }
}