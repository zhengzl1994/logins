package com.kido.service;

import com.kido.dao.BookDao;
import com.kido.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookDao{
    @Autowired
    private BookDao bookDao;
    @Override
    public List<Book> getAllBook() {
        return bookDao.getAllBook();
    }
}
