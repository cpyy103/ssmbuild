package com.kuang.service;

import com.kuang.pojo.Books;

import java.util.List;

public interface BookService {
    // 增加一本书
    int addBook(Books books);

    // 删除
    int deleteBookById(int id);

    // 更新
    int updateBook(Books books);

    // 查询
    Books queryBookById(int id);

    List<Books> queryAllBook();

    Books queryBookByName(String bookName);
}
