package com.xyw.code.base.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyw.code.base.api.entity.SysUserRole;
import com.xyw.code.base.server.mapper.SysUserRoleMapper;
import com.xyw.code.base.server.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public List<SysUserRole> selectUserRoleListByUserId(Integer userId) {
        return baseMapper.selectUserRoleListByUserId(userId);
    }
}
