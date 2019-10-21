package com.xyw.code.core.constant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 认证安全常量类
 * @Date: Create in 下午4:42 2019/10/18
 */
@Setter
@Getter
public class SecurityConstant implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String OAUTH_TOKEN_URL = "/oauth/token";

    public static final String PHONE_KEY = "phone";

    /**
     * noop是因为取出来的时候需要加密  然后加密方式是因为 PasswordEncoderFactories.createDelegatingPasswordEncoder()
     * sys_oauth_client_details 表的字段 {scrypt}
     */
    public static final String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    public static final String BASE_FIND = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND = BASE_FIND + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT = BASE_FIND + " where client_id = ?";


    public static final String LOGIN_QQ = "qq";
    public static final String LOGIN_WEIXIN = "weixin";
    public static final String LOGIN_GITEE = "gitee";
    public static final String LOGIN_GITHUB = "github";


    /**
     * redis认证客户端id前缀
     */
    public static final String REDIS_PREFIX_XPTX_OATH2_CLIENT_ID = "XPTX_OATH2_CLIENT_ID_";

}
