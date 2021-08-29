package com.urchin.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author urchin
 * @Description 主实体
 * @PROJECT_NAME evolution
 * @create 2021-05-16 21:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private  long id;
    private  String serial;
}