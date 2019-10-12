package com.xyw.code.base.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author 徐一炜
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户名称
     */
    private String name;

    /**
     * 租户编号
     */
    private String code;

    /**
     * 授权开始时间
     */
    private LocalDateTime startTime;

    /**
     * 授权结束时间
     */
    private LocalDateTime endTime;

    /**
     * 0正常 9-冻结
     */
    private Integer status;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
