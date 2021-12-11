package com.wang.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.server.entity.Type;
import com.wang.server.mapper.TypeMapper;
import com.wang.server.service.TypeService;
import org.springframework.stereotype.Service;

/**
 * @program: blogServer
 * @description: 实现
 * @author: Mr.Wang
 * @create: 2021-12-11 15:15
 **/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
