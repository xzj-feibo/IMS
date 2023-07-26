package com.xzj.vo;

import lombok.Data;


/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/8 17:15
 */
@Data
public class UserVo extends BaseVo{
    private Long id;

    private String userName;

    private String account;

    private Long deptId;

    private String userMobile;

    private String deptName;

    private Long roleId;

    private String roleName;
}
