package com.xyw.code.base.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyw.code.base.api.dto.UserDetailsInfo;
import com.xyw.code.base.api.entity.SysUser;
import com.xyw.code.base.server.mapper.SysUserMapper;
import com.xyw.code.base.server.service.ISysMenuService;
import com.xyw.code.base.server.service.ISysUserRoleService;
import com.xyw.code.base.server.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Override
    public SysUser findUserByUserIdOrUserNameOrPhone(String userIdOrUserNameOrPhone) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery().select()
                .eq(SysUser::getUserId,userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getUsername,userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getPhone,userIdOrUserNameOrPhone);
        return baseMapper.selectOne(select);
    }

    @Override
    public UserDetailsInfo findUserInfo(SysUser user) {
        UserDetailsInfo userDetailsInfo = new UserDetailsInfo();
        //获取菜单的perms
        Set<String> perms = findPermsByUserId(user.getUserId());
        //获取角色的id
        Set<String> roleIds = findRoleIdByUserId(user.getUserId());
        //返回信息
        perms.addAll(roleIds);
        userDetailsInfo.setSysUser(user);
        userDetailsInfo.setPermissions(perms);
        return userDetailsInfo;
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        Set<String> perms = menuService.findPermsByUserId(userId).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
        return perms;
    }

    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return userRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());    }
}
