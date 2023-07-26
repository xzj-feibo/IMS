package com.xzj.service;

import com.xzj.resp.ListResp;
import com.xzj.resp.Resp;

import java.util.List;

public interface IMenuService {

    ListResp selectMenuList();

    ListResp nodesList();

    Resp saveOrUpdate(Long menuId, Long parentId, String menuIcon, String menuUrl, String menuName, Integer menuOrder);

    Resp delete(List<Long> moduleIds);
}
