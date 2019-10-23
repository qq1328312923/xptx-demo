package com.xyw.code.authserver.security;

import com.xyw.code.auth.service.XptxSecurityUser;
import com.xyw.code.authserver.feign.RemoteUserService;
import com.xyw.code.base.api.dto.UserDetailsInfo;
import com.xyw.code.base.api.entity.SysUser;
import com.xyw.code.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Set;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 下午5:43 2019/10/18
 */
@Slf4j
@Service
public class XptxUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserDetailsInfo> info = remoteUserService.info(username);
        if(info==null || info.getData()==null || info.getData().getSysUser()==null){
            log.debug("登录用户:"+username+"不存在");
            throw new UsernameNotFoundException("登录用户:"+username+"不存在");
        }
        //得到用户信息
        UserDetailsInfo userDetailsInfo = info.getData();
        SysUser sysUser = userDetailsInfo.getSysUser();
        //得到该用户所拥有的的角色id集合 ROLE_role_id
        Set<String> permissions = userDetailsInfo.getPermissions();
        //把角色id转换成list
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
        //{bcrypt}数据库明文保存  取出来比较的时候密文
        return new XptxSecurityUser(sysUser.getUserId(),username,"{bcrypt}"+sysUser.getPassword(), authorities);
    }
}
