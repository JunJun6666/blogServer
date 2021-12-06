package com.wang.server.mapper;

import com.wang.server.common.dataoriginconfig.DataSource;
import com.wang.server.entity.Role;
import com.wang.server.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @DataSource(name = "b-database")
    List<Role> getRolesByAdminId(String id);

    @DataSource(name = "b-database")
    UserInfo getOne(String username);
}
