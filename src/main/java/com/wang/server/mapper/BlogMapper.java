package com.wang.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.server.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    IPage<Blog> queryPage(Page page, Map<String, Object> param);
}
