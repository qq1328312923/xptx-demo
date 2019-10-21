package com.xyw.code.auth.security;

import com.alibaba.fastjson.JSONObject;
import com.xyw.code.core.constant.SecurityConstant;
import com.xyw.code.core.constant.XptxConstant;
import com.xyw.code.redis.util.XptxRedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: oath2认证服务  获取客户端详情sevice 重写了，走缓存
 * @Date: Create in 下午4:31 2019/10/18
 */
public class XptxClientDetailsService extends JdbcClientDetailsService {

//    public XptxClientDetailsService(DataSource dataSource) {
//        super(dataSource);
//    }
//
//    /**
//     * 重写原生方法支持缓存
//     *
//     * @param clientId
//     * @return
//     * @throws InvalidClientException
//     */
//    @Override
//    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
//        String clientDetailsStr = (String) XptxRedisUtil.get(SecurityConstant.REDIS_PREFIX_XPTX_OATH2_CLIENT_ID+clientId);
//        if (StringUtils.isNotBlank(clientDetailsStr)) {
//            ClientDetails clientDetails = JSONObject.parseObject(clientDetailsStr,ClientDetails.class);
//            return clientDetails;
//        }
//        ClientDetails clientDetails1 = super.loadClientByClientId(clientId);
//        XptxRedisUtil.set(SecurityConstant.REDIS_PREFIX_XPTX_OATH2_CLIENT_ID+clientId, JSONObject.toJSONString(clientDetails1));
//        return clientDetails1;
//    }
private static final Map<String, ClientDetails> CLIENTS = new HashMap<>();

    public static void clearCache() {
        CLIENTS.clear();
    }
    public static void clearCache(String clientId) {
        CLIENTS.remove(clientId);
    }

    public XptxClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        //先判断缓存是否有  有的话 直接取缓存
        ClientDetails clientDetails = CLIENTS.get(clientId);
        if(clientDetails!=null){
            return clientDetails;
        }
        clientDetails = super.loadClientByClientId(clientId);
        CLIENTS.put(clientId,clientDetails);
        return clientDetails;
    }

}
