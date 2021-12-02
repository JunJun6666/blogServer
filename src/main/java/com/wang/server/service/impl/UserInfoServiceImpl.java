package com.wang.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.server.common.Result.R;
import com.wang.server.common.Result.ResultEnum;
import com.wang.server.common.jwt.JwtTokenUtil;
import com.wang.server.entity.LoginUser;
import com.wang.server.entity.Role;
import com.wang.server.entity.UserInfo;
import com.wang.server.mapper.UserInfoMapper;
import com.wang.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserInfoMapper userMapper;

    @Override
    public R login(LoginUser loginUser) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getUserName());
//        if (userDetails == null || !passwordEncoder.matches(user.getPassword(),userDetails.getPassword())){
//            return R.error().resultEnum(ResultEnum. USER_LOGIN_FAILED);
//        }

        if (userDetails == null || !Objects.equals(loginUser.getPassword() , userDetails.getPassword())){
            return R.error().resultEnum(ResultEnum. USER_LOGIN_FAILED);
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return R.ok().data("token",tokenMap);
    }

    @Override
    public UserInfo getAdminByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_name",username));
    }

    @Override
    public List<Role> getRolesByAdminId(String id) {
        return userMapper.getRolesByAdminId(id);
    }
}
