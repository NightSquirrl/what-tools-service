
package com.whattools.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.whattools.pojo.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("TokenService")
@Slf4j
public class TokenService {
    private static final String SALT = "nightsoul";

    public String getToken(UserDao user) {
        String token = "";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + user.getUserPassword()).getBytes());
        // 存入需要保存在token里的信息，这里把用户ID存入token
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(encryptPassword));
        // 使用HMAC256加密算法生成token,密钥是用户的密码Ï
        return token;
    }

    // 解析 token
    public Integer parseToken(String token) {
        try {
            // 解码并验证 token
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(getUserPasswordByToken(token)))  // 这里假设你有一个方法根据token解析出密码
                    .build()
                    .verify(token);  // 验证token的签名和过期时间等

            // 提取 token 中的用户ID（你可以根据需要从 payload 中提取其他信息）
            String userId = decodedJWT.getAudience().get(0);  // 假设用户ID在 audience 中
            return Integer.parseInt(userId);  // 返回用户ID

        } catch (Exception e) {
            // 处理 token 解析失败或验证失败的情况
            throw new RuntimeException("Invalid token", e);
        }
    }

    // 模拟通过 token 获取用户密码的过程（实际中你可能需要从数据库等地方获取）
    private String getUserPasswordByToken(String token) {
        // 假设我们通过 token 中的一些信息或者用户ID来查询数据库获取用户密码
        // 这里返回一个示例密码，实际中需要根据 token 中的内容查找用户信息
        return "userPassword";
    }
}