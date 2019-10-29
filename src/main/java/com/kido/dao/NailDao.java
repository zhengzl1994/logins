package com.kido.dao;

import com.kido.domain.Nail;
import org.springframework.stereotype.Repository;

@Repository
public interface NailDao {
    int insert(Nail nail);
    int countTel(String tel);
}
