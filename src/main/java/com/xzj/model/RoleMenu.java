package com.xzj.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class RoleMenu {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu.id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu.menu_id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    private Long menuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu.role_id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    private Long roleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu.id
     *
     * @return the value of role_menu.id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu.id
     *
     * @param id the value for role_menu.id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu.menu_id
     *
     * @return the value of role_menu.menu_id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu.menu_id
     *
     * @param menuId the value for role_menu.menu_id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu.role_id
     *
     * @return the value of role_menu.role_id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu.role_id
     *
     * @param roleId the value for role_menu.role_id
     *
     * @mbg.generated Fri Jul 14 10:08:46 CST 2023
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}