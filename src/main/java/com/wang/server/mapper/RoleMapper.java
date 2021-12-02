package com.wang.server.mapper;

import com.wang.server.dto.ResourceRoleDTO;
import com.wang.server.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<ResourceRoleDTO> listResourceRoles();
}
