package com.cirvor.learn.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {

            //获取前台过来的user信息
            com.cirvor.learn.pojo.User myUser = new ObjectMapper()
                    .readValue(req.getInputStream(), com.cirvor.learn.pojo.User.class);


            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            myUser.getUsername(),
                            myUser.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 请求校验成功后的处理
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        HashMap<String, String> result = new HashMap<>();

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant())) //设置有效期7天
                .signWith(SignatureAlgorithm.HS512, "rNpLVU6z3rPbPKN5MLykG2kh%YgqoFqc")
                .compact();

        result.put("Authorization", "Bearer " + token);

        //将生成token返回给前台
//        ResponseUtil.write(res, result);
        System.out.println(result);
    }
}
