
package com.whattools.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.whattools.pojo.UserDao;
import org.springframework.stereotype.Service;

@Service("TokenService")
public class TokenService {
    public String getToken(UserDao user) {
        String token="";
        // 存入需要保存在token里的信息，这里把用户ID存入token
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        // 使用HMAC256加密算法生成token,密钥是用户的密码
        return token;
    }
}