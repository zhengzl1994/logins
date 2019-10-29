package com.kido.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kido.domain.Login;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUse {

    //过期时间24小时
    private static final long overTime=1440*60*1000;
    //私钥uuid生成，确定唯一性
    private static final String  tokenSecRet="thefirsttoken123";

    /**
     * 生成token，用户退出后消失
     * @param name
     * @param userId
     * @return
     */
    public static String sign(String name,int userId){
        try {
            //设置过期时间
            Date date=new Date(System.currentTimeMillis()+overTime);
            //token私钥加密
            Algorithm algorithm= Algorithm.HMAC256(tokenSecRet);
            //设置头部信息
            Map<String,Object> requestHeader=new HashMap<>(2);
            requestHeader.put("type","JWT");
            requestHeader.put("alg","HS256");
//            long date1=new Date().getTime();

            //返回带有用户信息的签名
            return JWT.create().withHeader(requestHeader)
                    .withClaim("name",name)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String getToken(Login user) {
        String token="";
        try {
            token= JWT.create().withAudience(user.getUserId())
                    .sign(Algorithm.HMAC256(user.getPassWard()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 验证token是否正确
     * @param token
     * @return
     */
    public static  boolean tokenVerify(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC256(tokenSecRet);
            JWTVerifier verifier=JWT.require(algorithm).build();
            //验证
            DecodedJWT decodedJWT=verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取登陆用户token中的用户ID
     * @param token
     * @return
     */
    public static int getUserID(String token){
        DecodedJWT decodedJWT=JWT.decode(token);
        return decodedJWT.getClaim("userId").asInt();
    }

    //测试返回token字符串
    /*public static void main(String[] args){
      String token = TokenUse.sign("admin",123);
        System.out.println(token);
    }*/
}
