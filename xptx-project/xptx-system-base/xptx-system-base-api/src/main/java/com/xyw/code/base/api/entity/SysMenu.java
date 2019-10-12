package com.xyw.code.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author 徐一炜
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String perms;

    /**
     * 前端跳转URL
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    private String delFlag;

    /**
     * 是否为外链
     */
    private Boolean isFrame;

    /**
     * 是否缓存路由
     */
    private Boolean keepAlive;

    /**
     * 是否隐藏菜单
     */
    private Boolean hidden;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 聚合路由
     */
    private Boolean alwaysShow;


}
