package com.xyw.code.auth.config;

import com.xyw.code.auth.handler.XptxAuthExceptionEntryPoint;
import com.xyw.code.auth.handler.XptxCustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: oauth2资源服务器
 * @Date: Create in 下午5:28 2019/10/18
 */
@Primary
@Slf4j
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * security的鉴权排除的url列表
     */
    public static final String[] EXCLUDED_AUTH_PAGES = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/*/v2/api-docs",
            "/v2/api-docs",
            "/api/socket/**",
            "/log",
            "/*/api-docs",
            "/actuator/health",
            "/sendCode/**", "/mobile/login/**", "/socialSignUp", "/oauth/**", "/user/**",
            "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index","/captcha.jpg",
            "/*.html", "/**/*.html", "/**/*.css", "/**/*.js"
    };

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     * 只要满足上面的正则请求一律放过，不满足的一律认证
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(EXCLUDED_AUTH_PAGES).permitAll()
                .anyRequest()
                .authenticated()
                // 短信登录配置
                .and().csrf().disable();
    }

    /**
     * 资源获取异常时候的配置类
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //自定义Token异常信息 非法访问资源,访问此资源需要完全身份验证
                .authenticationEntryPoint(new XptxAuthExceptionEntryPoint())
                //没有权限 授权失败时返回信息
                .accessDeniedHandler(new XptxCustomAccessDeniedHandler());
    }
}
