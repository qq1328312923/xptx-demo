package com.xyw.code.base.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyw.code.base.api.entity.SysUserRole;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户id获取该用户的角色id集合
     * @param userId
     * @return
     */
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);
}
