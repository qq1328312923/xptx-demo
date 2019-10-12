package com.xyw.code.base.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 社交登录表
 * </p>
 *
 * @author 徐一炜
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 社交类型
     */
    private String providerId;

    /**
     * 社交的Id
     */
    private String providerUserId;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 头像地址
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
