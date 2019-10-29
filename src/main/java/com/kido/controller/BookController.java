package com.kido.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kido.common.JsonMsg;
import com.kido.domain.Book;
import com.kido.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/getAllBook")
    @ResponseBody
    public JsonMsg getAllBook(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(pageNo);
        // 表示从第pn查，每一页显示5条数据
        PageHelper.startPage(pageNo, 5);// 后面紧跟的这个查询就是分页查询
        //获取总数据
        List<Book> list= bookService.getAllBook();
        PageInfo page = new PageInfo(list, 1);
//        PageUtil page = new PageUtil(list,5,pn);// 5:表示每次只显示5页的导航菜单

        return JsonMsg.OK("获取成功",page);
    }


}
