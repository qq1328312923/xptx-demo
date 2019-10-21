package com.xyw.code.base.api.dto;

import com.xyw.code.base.api.entity.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Set;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: websecurity登陆之后保存的用户信息的dto
 * @Date: Create in 上午9:38 2019/10/21
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class UserDetailsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户的基本信息
     */
    private SysUser sysUser;

    /**
     * 用户权限
     */
    private Set<String> permissions;
}
