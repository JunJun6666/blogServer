package com.wang.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.server.common.Result.R;
import com.wang.server.entity.Blog;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
public interface BlogService extends IService<Blog> {

    R add(Blog blog);

    R queryPage(Map<String, Object> param);
}
