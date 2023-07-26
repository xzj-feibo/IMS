package com.xzj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/12 15:33
 */
@Data
public class BaseVo {
    private String createUserName;

    private String editUserName;

    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;
}
