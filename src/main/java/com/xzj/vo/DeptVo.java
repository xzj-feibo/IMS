package com.xzj.vo;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.util.Date;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/12 15:31
 */
@Data
public class DeptVo extends BaseVo {
    private Long id;

    private String deptName;

    private String deptNo;

}
