package com.cirvor.learn.service.impl;

import com.cirvor.learn.mapper.UserMapper;
import com.cirvor.learn.pojo.JwtUser;
import com.cirvor.learn.pojo.Role;
import com.cirvor.learn.pojo.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public JwtUser loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userMapper.findUserByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException(String.format("'%s'.这个用户不存在", s));
        }
        List<SimpleGrantedAuthority> collect = user.getRoles()
                .stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), collect);
    }
}
