package com.xyw.code.authserver.config;

import com.xyw.code.authserver.security.XptxClientDetailsService;
import com.xyw.code.core.constant.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 认证配置类
 * @Date: Create in 下午9:13 2019/10/14
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        XptxClientDetailsService clientDetailsService = new XptxClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstant.DEFAULT_SELECT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstant.DEFAULT_FIND);
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services),告诉spring security token的生成方式。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //指定token存储位置
                .tokenStore(tokenStore())
                // 配置JwtAccessToken转换器
                .accessTokenConverter(accessTokenConverter())
//                .tokenEnhancer(tokenEnhancerChain)
                //指定认证管理器,当你选择了资源所有者密码（password）授权类型的时候，需设置这个属性注入一个 AuthenticationManager 对象。
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

                // 配置tokenServices参数
        //        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //        tokenServices.setTokenStore(endpoints.getTokenStore());
        //        // 是否支持刷新
        //        tokenServices.setSupportRefreshToken(true);
        //        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        //        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        //        // 1分钟
        //        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(20));
        //        endpoints.tokenServices(tokenServices);

        //        endpoints.exceptionTranslator(new PrexCustomWebResponseExceptionTranslator());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    /**
     * 它可以把令牌相关的数据进行编码，声明TokenStore实现
     * 设置令牌用jwt token编码
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
}
