package com.kido.WebConfig;


import com.kido.Interceptor.MyHandelInterceptor;
import com.kido.test.TokenUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringBootWebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private MyHandelInterceptor myHandelInterceptor;

    public  SpringBootWebMvcConfig(MyHandelInterceptor myHandelInterceptor){
        this.myHandelInterceptor = myHandelInterceptor;
    }
    /*@Bean
    public MyHandelInterceptor getMyHandelInterceptor(){
        return  new MyHandelInterceptor();
    }*/

    //spring mvc拦截器的配置
    @Override
    public void addInterceptors(InterceptorRegistry registry){

        /**调用我们创建的MyHandelInterceptor
         * addPathPatterns("/**)的意思是这个链接下的都要进入到MyHandelInterceptor里面去执行
         * excludePathPatterns("/login")的意思是login的url可以不用进入到SessionInterceptor中，直接放过执行
         */
       registry.addInterceptor(myHandelInterceptor).addPathPatterns("/**")
               .excludePathPatterns("/act/login","/mapper/**");
//       .excludePathPatterns("/act/login","/css/**","/js/**","/img/**","/mapper/**");
        super.addInterceptors(registry);
    }


}
