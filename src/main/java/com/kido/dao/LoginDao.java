package com.kido.dao;
import com.kido.domain.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  LoginDao {
    public Login loginByName(String userId);
    public Login loginByNameAndPassWard(@Param("name") String name,@Param("passWard") String passWard);
    public void insertUser(@Param("name")String name,@Param("secret")String secret);
    public void updateLogin(@Param("newName")String newName,@Param("passWard")String passWard);
}
