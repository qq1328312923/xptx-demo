package com.xyw.code.base.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 客户端信息表
 * </p>
 *
 * @author 徐一炜
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端Id
     */
    private String clientId;

    private String resourceIds;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 域
     */
    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    /**
     * 权限
     */
    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    /**
     * 是否自动放行
     */
    private String autoapprove;


}
