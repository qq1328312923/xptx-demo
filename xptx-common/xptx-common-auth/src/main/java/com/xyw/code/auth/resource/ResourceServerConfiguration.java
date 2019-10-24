package com.xyw.code.auth.resource;

import com.xyw.code.auth.handler.XptxAuthExceptionEntryPoint;
import com.xyw.code.auth.handler.XptxCustomAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: oauth2资源服务器 这个鉴权服务主要给业务服务用 因为业务服务登录的时候有个/user/info的接口去获取这个用户的权限
 * 并且还有一个@PreAuthor接口去鉴权 为了防止/user/info被拦截 因此需要重写这个服务
 * @Date: Create in 下午5:10 2019/10/24
 */
@Slf4j
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration   extends ResourceServerConfigurerAdapter {

    /**
     * security的鉴权排除的url列表
     */
    private static final String[] EXCLUDED_AUTH_PAGES = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/*/v2/api-docs",
            "/v2/api-docs",
            "/api/socket/**",
            "/log",
            "/actuator/**",
            "/*/api-docs",
            "/sendCode/**", "/mobile/login/**", "/oauth/**", "/repeatCheck/check",
            "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index",
            "/*.html", "/**/*.html", "/**/*.css", "/**/*.js","/user/info/**","/user/social/info"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(EXCLUDED_AUTH_PAGES).permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(new XptxAuthExceptionEntryPoint())
                .accessDeniedHandler(new XptxCustomAccessDeniedHandler());
    }

    @Bean
    public TokenStore
    tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;

    }
}
