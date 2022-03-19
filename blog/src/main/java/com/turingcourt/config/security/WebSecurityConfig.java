package com.turingcourt.config.security;

import com.alibaba.fastjson.JSON;
import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultCode;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserService;
import com.turingcourt.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


/**
 * @author Cyanogen
 * @description SpringSecurity配置类
 * @date 2022/3/8 23:00
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserLoginServiceImpl loginService;

    @Autowired
    UserService userService;

    /**
     * 登录失败处理逻辑
     */
    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;


    /**
     * 自定义登录过滤器
     *
     * @return 过滤器类
     * @throws Exception 抛出异常
     */
    @Bean
    LoginFilter loginFilter() throws Exception {

        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        //登录成功处理
        loginFilter.setAuthenticationSuccessHandler((req, res, auth) -> {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("登录成功");
            //返回json数据
            JsonResult result = ResultTool.success(user);
            //处理编码方式，防止中文乱码的情况
            res.setContentType("text/json;charset=utf-8");
            //塞到HttpServletResponse中返回给前台
            res.getWriter().write(JSON.toJSONString(result));
        });
        //登录失败处理
        loginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        return loginFilter;
    }

    /**
     * 把数据库查出的用户信息交给SpringSecurity处理
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //把自定义的userDetailsService注入到配置文件中
        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //取消自动重定向到SpringSecurity自带的登录页
        http.exceptionHandling().authenticationEntryPoint(((request, response, authException) -> {
            JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
        }));

        //登录验证
        http.authorizeRequests()
                //访问限制
                .antMatchers("/user/**").authenticated()
                .antMatchers("/blog/insertBlog").authenticated()
                .antMatchers("/comment/insert").authenticated()
                .antMatchers("/comment/reply").authenticated()
                .antMatchers("/comment/delete").authenticated()
                .antMatchers("/like/**").authenticated()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated();

        //异常处理(权限拒绝、登录失效等)
        http.exceptionHandling()
                //权限拒绝处理逻辑
                .accessDeniedHandler((req, res, e) -> {
                    res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    //无权限
                    JsonResult result = ResultTool.fail(ResultCode.NO_PERMISSION);
                    res.setContentType("text/json;charset=utf-8");
                    res.getWriter().write(JSON.toJSONString(result));
                })
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint((req, res, auth) -> {
                    JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
                    res.setContentType("text/json;charset=utf-8");
                    res.getWriter().write(JSON.toJSONString(result));
                });

        //限制同一账号只能一个用户使用
        http.sessionManagement()
                //同一账号同时登录最大用户数
                .maximumSessions(1)
                //会话信息过期策略会话信息过期策略(账号被挤下线)
                .expiredSessionStrategy((e) -> {
                    JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
                    HttpServletResponse httpServletResponse = e.getResponse();
                    httpServletResponse.setContentType("text/json;charset=utf-8");
                    httpServletResponse.getWriter().write(JSON.toJSONString(result));
                });

        //登出
        http.logout()
                //允许所有用户
                .permitAll()
                //登出成功处理逻辑
                .logoutSuccessHandler((req, res, auth) -> {
                    System.out.println("登出成功");
                    JsonResult result = ResultTool.success();
                    res.setContentType("text/json;charset=utf-8");
                    res.getWriter().write(JSON.toJSONString(result));
                })
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies();

        //替换原来的过滤器
        http.addFilterAfter(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        //解决跨域
        http.cors().and().csrf().disable().headers().frameOptions().disable();
    }
}
