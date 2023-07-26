package com.xzj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzj.exception.ImsAuthException;
import com.xzj.mapper.RoleMapper;
import com.xzj.mapper.RoleMenuMapper;
import com.xzj.mdc.MDCKey;
import com.xzj.model.*;
import com.xzj.resp.*;
import com.xzj.service.IMenuService;
import com.xzj.service.IRoleService;
import com.xzj.vo.RoleVo;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/12 18:33
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper mapper;

    @Resource
    private IMenuService service;

    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Override
    public PageResp<List<RoleVo>> selectRoleList(Integer page, Integer limit, String roleName, String roleNo) {
        PageHelper.startPage(page,limit);
        List<RoleVo> list = mapper.selectRoleList(roleName,roleNo);
        PageInfo<RoleVo> info = new PageInfo<>(list);

        PageResp<List<RoleVo>> resp = new PageResp<>();
        resp.setCount(info.getTotal());
        resp.setData(list);
        return resp;
    }

    @Override
    public Resp saveOrUpdate(Long roleId, String roleName, String roleNo) {
        Role role = new Role();
        role.setRoleCode(roleNo);
        role.setRoleName(roleName);
        //新增
        if (roleId == null){
            role.setCreateTime(new Date());
            //初始密码
            role.setCreateUser(Long.valueOf(MDC.get(MDCKey.USER_ID)));
            int insert = mapper.insert(role);
            Resp.toReturn(insert>0?"成功":"失败",insert>0);
        }
        //更新
        role.setId(roleId);
        role.setUpdateTime(new Date());
        role.setUpdateUser(Long.valueOf(MDC.get(MDCKey.USER_ID)));
        int update = mapper.updateByPrimaryKey(role);
        return Resp.toReturn(update>0?"成功":"失败",update>0);
    }
//
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Resp delete(Long roleId) {
        //1.删除角色
        int ret = mapper.deleteByPrimaryKey(roleId);
        //2.删除角色所拥有的所有权限
        RoleMenuExample roleMenuExample = new RoleMenuExample();
        roleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
        int deleteCount = roleMenuMapper.deleteByExample(roleMenuExample);

        return Resp.toReturn(ret>0?"成功":"失败",ret>0);
    }

    @Override
    public TreeResp roleRight(Long roleId) {
        ListResp listResp = service.selectMenuList();
        TreeResp ret = new TreeResp<>();
        ret.setData(listResp.getData());

        List<RoleMenu> roleMenus = roleMenuMapper.roleRight(roleId);
        ret.setList(roleMenus);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Resp roleRightSave(Long roleId, List<Long> menuIds) {
        //删除之前的记录
        RoleMenuExample roleMenuExample = new RoleMenuExample();
        roleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
        int deleteCount = roleMenuMapper.deleteByExample(roleMenuExample);
         //批量插入数据
        int ret = 0;
        for (Long menuId : menuIds) {
            ret+=roleMenuMapper.insert(new RoleMenu(null,menuId,roleId));
        }
        return Resp.toReturn(ret == menuIds.size()?"成功":"失败",ret == menuIds.size());
    }

    @Override
    public ListResp selectRoleListNoParams() {
        List<Role> ret = mapper.selectByExample(new RoleExample());
        ListResp rsp = new ListResp();
        rsp.setData(ret);
        return rsp;
    }
}
