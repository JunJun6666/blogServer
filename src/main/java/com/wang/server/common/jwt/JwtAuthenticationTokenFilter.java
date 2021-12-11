package com.wang.server.common.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * @author WJ
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private  String tokenHeader;
    @Value("${jwt.tokenHead}")
    private  String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("进入jwt登录授权过滤");
        String authHeader = request.getHeader(tokenHeader);

        //存在token
        if(null != authHeader){
            if(authHeader.startsWith(tokenHead)){
                String token = authHeader.substring(tokenHead.length());
                System.err.println(authHeader);
                String username = jwtTokenUtil.getUserNameByToken(token);
                //token存在用户名，未登录
                if(null != username && null == SecurityContextHolder.getContext().getAuthentication()){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    //验证token是否有效，重新设置用户对象
                    if(jwtTokenUtil.validateToken(token , userDetails)){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
//            else{
//                //token格式问题，重新登录
//                try {
//                    Tools.addJson(response , "1001" , "请重新登录");
//                    return;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
        filterChain.doFilter(request , response);
    }
}
