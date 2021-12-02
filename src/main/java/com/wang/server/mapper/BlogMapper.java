package com.wang.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.server.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

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

}
