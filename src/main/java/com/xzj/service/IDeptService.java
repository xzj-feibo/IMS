package com.xzj.service;

import com.xzj.resp.*;

public interface IDeptService {
    /**
     * 查询所有部门
     * @param page 页面数
     * @param limit 每页数量
     * @param deptName 部门名
     * @param deptNo 部门号
     * @return
     */
    PageResp selectDeptList(Integer page, Integer limit, String deptName, String deptNo);

    Resp saveOrUpdate(Long deptId, String deptName, String deptNo);

    Resp delete(Long deptId);

    ListResp selectDeptListNoParams();
}
