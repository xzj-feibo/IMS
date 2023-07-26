package com.xzj.service.impl;

import com.xzj.mapper.MenuMapper;
import com.xzj.mdc.MDCKey;
import com.xzj.model.*;
import com.xzj.resp.ListResp;
import com.xzj.resp.Resp;
import com.xzj.service.IMenuService;
import com.xzj.vo.MenuNodeVo;
import com.xzj.vo.MenuVo;

import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/13 11:44
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Resource
    MenuMapper mapper;

    @Override
    public ListResp selectMenuList() {
        //得到当前用户在系统中的角色id
        Long roleId = Long.valueOf(MDC.get(MDCKey.USER_ROLE_ID));
        List<Menu> menus = mapper.selectByRoleId(roleId);
        /**
         * 先找没有儿子的出来，再去找儿子
         */
//        List<Menu> menus = mapper.selectByExample(new MenuExample());
        //父级菜单
        List<MenuVo> parentMenus = new ArrayList<>();
        //子级菜单
        List<MenuVo> sonMenus = new ArrayList<>();
        for (Menu menu : menus) {
            MenuVo menuVo = new MenuVo();
            //拷贝，字段名称和类型需要一致
            BeanUtils.copyProperties(menu,menuVo);
            if (menu.getPid() == null){
                //没有pid，说明没有父级，已经是顶级菜单，添加到父级菜单中
                parentMenus.add(menuVo);
            }
            //添加到儿子菜单中
            sonMenus.add(menuVo);
        }
        //排好序的父级菜单
        //流式操作，加快集合处理速度
        List<MenuVo> parentOrderMenus = parentMenus.stream().sorted(Comparator.
                        comparing(MenuVo::getOrderValue)).
                collect(Collectors.toList());
        for (MenuVo orderMenu : parentOrderMenus) {
            //根据父级菜单的id和所有子菜单得到该父级菜单下的子菜单
            List<MenuVo> child = getChild(orderMenu.getId(),sonMenus);
            //排好序的子菜单
            List<MenuVo> collect = child.stream().
                    sorted(Comparator.comparing(MenuVo::getOrderValue))
                    .collect(Collectors.toList());
            orderMenu.setMenus(collect);
        }
        ListResp<List<MenuVo>> listResp = new ListResp();
        listResp.setData(parentOrderMenus);
        return listResp;
    }

    @Override
    public ListResp nodesList() {
        List<MenuNodeVo> ret = mapper.selectNodes();
        ListResp resp = new ListResp<>();
        resp.setData(ret);
        return resp;
    }

    @Override
    public Resp saveOrUpdate(Long menuId, Long parentId, String menuIcon, String menuUrl, String menuName, Integer menuOrder) {
        Menu menu = new Menu();
        menu.setPid(parentId);
        menu.setIcon(menuIcon);
        menu.setUrl(menuUrl);
        menu.setMenuName(menuName);
        menu.setOrderValue(menuOrder);
        //新增
        if (menuId == null){
            int insert = mapper.insert(menu);
            Resp.toReturn(insert>0?"成功":"失败",insert>0);
        }
        //更新
        menu.setId(menuId);
        int update = mapper.updateByPrimaryKey(menu);
        return Resp.toReturn(update>0?"成功":"失败",update>0);
    }

    @Override
    public Resp delete(List<Long> moduleIds) {
        int sum = 0;
        for (Long moduleId : moduleIds) {
            int ret = mapper.deleteByPrimaryKey(moduleId);
            sum+=ret;
        }
        return Resp.toReturn(sum == moduleIds.size()?"成功":"失败",sum == moduleIds.size());
    }

    /**
     * 找属于该菜单id的 儿子
     * @param menuId
     * @param allMenus
     * @return
     */
    public List<MenuVo> getChild(Long menuId, List<MenuVo> allMenus){
        //找最近的儿子数据出来
        List<MenuVo> childMenus = new ArrayList<>();
        for (MenuVo allMenu : allMenus){
            if (allMenu.getPid() == menuId){
                childMenus.add(allMenu);
            }
        }
        if (childMenus.size() == 0){
            return childMenus;
        }
        //还要找儿子的儿子
        for (MenuVo childMenu : childMenus) {
            //说明还不是最低层的儿子
            if (StringUtils.isEmpty(childMenu.getUrl())){
                //递归
                childMenu.setMenus(getChild(childMenu.getId(),allMenus));
            }
        }
        return childMenus;
    }
}
