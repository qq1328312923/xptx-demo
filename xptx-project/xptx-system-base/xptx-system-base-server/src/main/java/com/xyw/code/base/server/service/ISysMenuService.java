package com.xyw.code.base.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyw.code.base.api.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户id获取用户菜单的标志
     * @param userId
     * @return
     */
    List<String> findPermsByUserId(Integer userId);
}
