package com.wang.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.server.entity.Blog;
import com.wang.server.mapper.BlogMapper;
import com.wang.server.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
