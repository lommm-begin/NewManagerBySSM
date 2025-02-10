package com.example.web.controller;

import com.example.web.pojo.AllNews;
import com.example.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NewsIndexController {

    @Autowired
    @Qualifier("newsServiceByMybatisImpl")
    private NewsService newsService;

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        List<AllNews> allNews = newsService.showAllNews();
        System.out.println(allNews);
        modelMap.addAttribute("allist", allNews);
        return "index";
    }
}
