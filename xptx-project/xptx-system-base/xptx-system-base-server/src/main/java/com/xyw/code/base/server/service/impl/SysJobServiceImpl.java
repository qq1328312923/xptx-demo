package com.xyw.code.base.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyw.code.base.api.entity.SysJob;
import com.xyw.code.base.server.mapper.SysJobMapper;
import com.xyw.code.base.server.service.ISysJobService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位管理 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements ISysJobService {

}
