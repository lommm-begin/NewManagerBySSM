package com.example.web.controller;

import com.example.web.pojo.AllNews;
import com.example.web.service.NewsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NewsIndexController {

    @Autowired
    private DaoContextHandler daoContextHandler;

    //获取对应的Dao实现类
    @Value("${Dao.daoDefault}")
    String daoDefault;

    private NewsService newsService;

    @PostConstruct
    public void init() {
        this.newsService = daoContextHandler.setNewsService(daoDefault).getNewsService();
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        List<AllNews> allNews = newsService.showAllNews();

        modelMap.addAttribute("allist", allNews);
        return "index";
    }
}
