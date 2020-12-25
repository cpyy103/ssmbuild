package com.kuang.dao;

import com.kuang.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    // 增加一本书
    int addBook(Books books);

    // 删除
    int deleteBookById(@Param("bookId") int id);

    // 更新
    int updateBook(Books books);

    // 查询
    Books queryBookById(@Param("bookId") int id);

    // 查询所有的书
    List<Books> queryAllBook();

    Books queryBookByName(@Param("bookName") String name);
}
