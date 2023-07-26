package com.xzj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/13 10:48
 */
@Data
public class MenuVo {
    @JsonProperty("menuid")
    private Long id;

    private String icon;

    @JsonProperty("menuname")
    private String menuName;

    private String hasThird;

    private String url;

    private Long pid;

    private Integer orderValue;

    private List<MenuVo> menus;//孩子
}
