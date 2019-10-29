package com.kido.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.kido.domain.Login;
import com.kido.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/zzl")
public class LoginController {
    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    private LoginService loginService;

    /**
     * 登录功能
     * @param username
     * @param passWord
     * @param code
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginPage",method = RequestMethod.POST)
    public String loginVerify(String username, String passWord,String code, HttpServletRequest request){
        String identCode = (String) request.getSession().getAttribute("verifyCode");
        System.out.println(identCode);

        //根据姓名查询用户信息
        Login login = loginService.loginByName(username);
        System.out.println(login);
//        if(username.equals("admin") && passWord.equals("123123")&& identCode.equals(code)){
        if(login!=null){
            //如果不为空 则进行用户名和密码的匹配
            System.out.println("****进入判断1****");
//            System.out.println(21323);
          if(username.equals(login.getName())&& passWord.equals(login.getPassWard())) {
              //最后进行验证码的匹配
              if(identCode.equals(code)){
                  System.out.println("****进入判断2****");
//            return "redirect:/LoginError.html";
                  return "redirect:/success.html";
              }else{
                  System.out.println("****进入判断3****");
                  //在登录失败的时候将当前输入的用户名存放进session中
                  request.getSession().setAttribute("username",username);
                  request.getSession().setAttribute("passWard",passWord);
                  String newName = (String) request.getSession().getAttribute("username");
                  System.out.println(newName);
                  return "redirect:/LoginError.html";
              }

         }else {
              System.out.println("****进入判断4****");
              //在登录失败的时候将当前输入的用户名存放进session中
              request.getSession().setAttribute("username",username);
              request.getSession().setAttribute("passWard",passWord);
              String newName = (String) request.getSession().getAttribute("username");
              System.out.println(newName);
              return "redirect:/LoginError.html";
          }
          //如果为空进入进入登录失败页面
        } else {
            System.out.println("****进入判断5****");
            //在登录失败的时候将当前输入的用户名存放进session中
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("passWard",passWord);
            String newName = (String) request.getSession().getAttribute("username");
            System.out.println(newName);
//            return "redirect:/success.html";
            return "redirect:/LoginError.html";
        }

    }

    /**
     * 获取随机验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @param model
     * @throws Exception
     */
    @RequestMapping("/getCode")
    public void getCode (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse , Model model) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", createText);
            model.addAttribute("createText",createText);
            System.out.println(createText);
            //使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组

            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();


    }

    /**
     * 注册用户
     * @param name
     * @param secret
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public  String addUser(String name,String secret){
        Login login = loginService.loginByNameAndPassWard(name,secret);
        System.out.println(111);
        if(name!="" && secret!=""){
            if(login!=null) {
                    return "exist";
            }else{
                loginService.insertUser(name, secret);
                return "success";
            }
        }else{
            return "fail";
        }

    }

    /**
     * 修改密码(找回密码)
     * @param passWard
     * @param request
     * @return
     */
    @RequestMapping("/findSecret")
    @ResponseBody
    public String updateSecret(String passWard,HttpServletRequest request){
        String newName = (String) request.getSession().getAttribute("username");
        System.out.println(newName);
        if(passWard!="" && newName!=null){
        loginService.updateLogin(newName,passWard);
        return "success";
        }else{
            return "fail";
        }
    }

}
