package com.kido.controller;

import com.kido.common.JsonMsg;
import com.kido.domain.Login;
import com.kido.service.LoginService;
import com.kido.test.TokenUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public JsonMsg login(String name, String passWard){
        //验证用户登陆信息于数据库中的用户信息是否一致
        Login ub = loginService.loginByNameAndPassWard(name, passWard);
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(ub);
        if (ub == null) {
            return JsonMsg.ERROR("message","登录失败用户不存在");
        }else{
            if(!ub.getPassWard().equals(passWard)){
                return  JsonMsg.ERROR("message","密码错误,请重新登录");
            }else{
                String token  = new TokenUse().getToken(ub);
                map.put("token",token);
                return JsonMsg.OK("登录成功",map);
            }
        }
    }
}
