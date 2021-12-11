package com.wang.server.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.server.common.Result.R;
import com.wang.server.common.page.PageUtils;
import com.wang.server.common.utils.LoginUtils;
import com.wang.server.entity.Blog;
import com.wang.server.mapper.BlogMapper;
import com.wang.server.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public R add(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setCreateUser(LoginUtils.getUserId());
        blog.setStatus("Y");
        save(blog);
        return R.ok();
    }

    @Override
    public R queryPage(Map<String, Object> param) {
        IPage<Blog> iPage = blogMapper.queryPage(PageUtils.getPage(param), param);
        return PageUtils.getPageResult(iPage);
    }
}
