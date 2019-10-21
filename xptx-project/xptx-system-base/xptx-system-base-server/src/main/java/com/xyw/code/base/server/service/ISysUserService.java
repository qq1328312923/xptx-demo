package com.xyw.code.base.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyw.code.base.api.dto.UserDetailsInfo;
import com.xyw.code.base.api.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户id或者用户名称或者手机号码来查询用户
     * @param userIdOrUserNameOrPhone
     * @return
     */
    SysUser findUserByUserIdOrUserNameOrPhone(String userIdOrUserNameOrPhone);

    /**
     * 根据用户来获取用户授权信息 给授权服务器使用
     * @param user
     * @return
     */
    UserDetailsInfo findUserInfo(SysUser user);

    /**
     * 根据用户id获取该用户所拥有的菜单标志
     * @param userId
     * @return
     */
    Set<String> findPermsByUserId(Integer userId);

    /**
     * 根据用户id获取该用户的所拥有的角色id
     * @param userId
     * @return
     */
    Set<String> findRoleIdByUserId(Integer userId);
}
