package com.kido.service;

import com.kido.dao.NailDao;
import com.kido.domain.Nail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NailService implements NailDao {
    @Autowired
    private NailDao nailDao;
    @Override
    public int insert(Nail nail) {
        return nailDao.insert(nail);
    }

    @Override
    public int countTel(String tel) {
        return nailDao.countTel(tel);
    }
}
