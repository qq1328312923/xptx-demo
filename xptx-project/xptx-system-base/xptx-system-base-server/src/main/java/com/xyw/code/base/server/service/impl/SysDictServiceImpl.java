package com.xyw.code.base.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyw.code.base.api.entity.SysDict;
import com.xyw.code.base.server.mapper.SysDictMapper;
import com.xyw.code.base.server.service.ISysDictService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

}
