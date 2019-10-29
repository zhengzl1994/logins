package com.kido.dao;

import com.kido.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    List<Book> getAllBook();
}
