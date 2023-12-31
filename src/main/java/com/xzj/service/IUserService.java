package com.xzj.service;

import com.xzj.resp.*;


/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/6 18:33
 */
public interface IUserService {
    LoginResp login(String account, String password);

    /**
     * 查询用户列表接口
     * @param page
     * @param limit
     * @param userName
     * @param userMobile
     * @return
     */
    PageResp selectUserList(Integer page,Integer limit,String userName,String userMobile);

    /**
     * 更新和新增
     * @param userId 通过用户id 判断
     * @param account
     * @param userName
     * @param userMobile
     * @param roleId
     * @param deptId
     * @return
     */
    Resp saveOrUpdate(Long userId, String account, String userName, String userMobile, Long roleId, Long deptId);

    Resp delete(Long userId);

    Resp resetPwd(Long userId);
}
