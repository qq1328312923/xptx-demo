package com.xyw.code.auth.config;

import com.xyw.code.auth.security.XptxUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: spring-security安全认证配置类  这个的目的为了得到认证的用户和用户账号密码 放入认证管理器
 * @Date: Create in 下午5:42 2019/10/18
 */
@Order(90)
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private XptxUserDetailsServiceImpl xptxUserDetailsService;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    /**
     * password 支持多种编码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 这个方法是去数据库查询用户的密码，做权限验证
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void config(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //设置UserDetailsService以及密码规则
        authenticationManagerBuilder
                .userDetailsService(xptxUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
