package com.kido.service;

import com.kido.dao.LoginDao;
import com.kido.domain.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;
    public Login loginByName(String userId){
        return loginDao.loginByName(userId);
    }


    public Login loginByNameAndPassWard(@Param("name")String name, @Param("passWard")String passWard){
        return loginDao.loginByNameAndPassWard(name,passWard);
    }

    public void insertUser (@Param("name") String name,@Param("secret") String secret){
         loginDao.insertUser(name,secret);
    }
    public  void updateLogin(@Param("newName")String newName,@Param("passWard")String passWard){
        loginDao.updateLogin(newName, passWard);
    }
}
