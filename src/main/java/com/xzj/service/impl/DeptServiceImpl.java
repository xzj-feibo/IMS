package com.xzj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzj.mapper.DeptMapper;
import com.xzj.mdc.MDCKey;
import com.xzj.model.Dept;
import com.xzj.model.DeptExample;
import com.xzj.resp.*;
import com.xzj.service.IDeptService;
import com.xzj.vo.DeptVo;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/12 15:26
 */
@Service
public class DeptServiceImpl implements IDeptService {
    @Resource
    private DeptMapper mapper;
    @Override
    public PageResp<List<DeptVo>> selectDeptList(Integer page, Integer limit, String deptName, String deptNo) {
        PageHelper.startPage(page,limit);
        List<DeptVo> list = mapper.selectDeptList(deptName,deptNo);
        PageInfo<DeptVo> info = new PageInfo<>(list);

        PageResp<List<DeptVo>> resp = new PageResp<>();
        resp.setCount(info.getTotal());
        resp.setData(list);
        return resp;
    }

    @Override
    public Resp saveOrUpdate(Long deptId, String deptName, String deptNo) {
        Dept dept = new Dept();
        dept.setDeptCode(deptNo);
        dept.setDeptName(deptName);
        //新增
        if (deptId == null){
            dept.setCreateTime(new Date());
            //初始密码
            dept.setCreateUser(Long.valueOf(MDC.get(MDCKey.USER_ID)));
            int insert = mapper.insert(dept);
            Resp.toReturn(insert>0?"成功":"失败",insert>0);
        }
        //更新
        dept.setId(deptId);
        dept.setUpdateTime(new Date());
        dept.setUpdateUser(Long.valueOf(MDC.get(MDCKey.USER_ID)));
        int update = mapper.updateByPrimaryKey(dept);
        return Resp.toReturn(update>0?"成功":"失败",update>0);
    }

    @Override
    public Resp delete(Long deptId) {
        int ret = mapper.deleteByPrimaryKey(deptId);
        return Resp.toReturn(ret>0?"成功":"失败",ret>0);
    }

    @Override
    public ListResp selectDeptListNoParams() {
        List<Dept> ret = mapper.selectByExample(new DeptExample());
        ListResp resp = new ListResp<>();
        resp.setData(ret);
        return resp;
    }
}
