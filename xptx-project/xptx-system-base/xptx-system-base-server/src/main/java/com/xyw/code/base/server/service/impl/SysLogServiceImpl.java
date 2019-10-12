package com.xyw.code.base.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyw.code.base.api.entity.SysLog;
import com.xyw.code.base.server.mapper.SysLogMapper;
import com.xyw.code.base.server.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
