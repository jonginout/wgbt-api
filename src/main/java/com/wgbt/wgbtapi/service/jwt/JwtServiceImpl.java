package com.wgbt.wgbtapi.service.jwt;


import com.wgbt.wgbtapi.common.UnauthorizedException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService{

    private static final String SALT =  "luvookSecret";

    @Override
    public <T> String create(String key, T data, String subject){
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(subject)
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnauthorizedException();
        }

        return key;
    }

    @Override
    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            System.out.println("값없는 토큰");
            throw new UnauthorizedException();

			/*개발환경
			Map<String,Object> testMap = new HashMap<>();
			testMap.put("memberId", 2);
			return testMap;*/
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        return value;
    }

    @Override
    public boolean isUsable(String jwt) {
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
            return true;

        }catch (Exception e) {
            System.out.println("못쓰는 토큰");
            throw new UnauthorizedException();
        }
    }

    @Override
    public Long getUserNo() {
        return (Long)this.get("userInfo").get("no");
    }

}