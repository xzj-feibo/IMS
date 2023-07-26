package com.xzj.resp;

import lombok.Data;


/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/8 17:05
 */
@Data
public class ListResp<T> extends Resp{
    private T data;
}
