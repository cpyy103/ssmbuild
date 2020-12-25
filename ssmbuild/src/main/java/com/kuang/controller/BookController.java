package com.kuang.controller;

import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //    controller调用service层
    @Autowired // 自动装配
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    // 查询所有书籍
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        System.out.println(list);
        return "allBook";
    }

    // 跳转到添加书籍界面
    @RequestMapping("/toAddBook")
    public String toAddPage() {
        return "addBook";
    }

    // 添加书籍请求
    @RequestMapping("/addBook")
    public String addBook(Books books) {
        System.out.println("addBook -> " + books);
        bookService.addBook(books);
        return "redirect:/book/allBook"; //重定向
    }

    // 跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdatePage(int id, Model model) {
        Books book = bookService.queryBookById(id);
        System.out.println("ready to update -> " + book);
        model.addAttribute("QBook", book);
        return "updateBook";
    }

    // 修改数据
    @RequestMapping("/updateBook")
    public String updateBook(Books book) {
        System.out.println("updateBook -> " + book);
        int i = bookService.updateBook(book);
        if (i > 0) {
            System.out.println("update book successfully");
        }
        return "redirect:/book/allBook"; //重定向
    }

    @RequestMapping("/deleteBook/{bookId}")   // Restful风格
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model) {
        List<Books> list = new ArrayList<>();
        Books books = bookService.queryBookByName(queryBookName);
        if (books == null) {
            list = bookService.queryAllBook();
            System.out.println("未查到对应书籍");
            model.addAttribute("error", "未查到");
        } else {
            System.out.println("queryBook -> " + books);
            list.add(books);
        }

        model.addAttribute("list", list);
        return "allBook";

    }

}
