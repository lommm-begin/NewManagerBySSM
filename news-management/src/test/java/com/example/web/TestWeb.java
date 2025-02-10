package com.example.web;

import com.example.web.config.SpringConfig;
import com.example.web.mapper.NewsdetailMapper;
import com.example.web.pojo.AllNews;
import com.example.web.service.Impl.NewsServiceByMybatisImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@WebAppConfiguration
public class TestWeb {

    @Autowired
    private NewsServiceByMybatisImpl newsServiceByMybatisImpl;

    @Autowired
    private NewsdetailMapper newsdetailMapper;

    @Test
    public void testSpringConfig() {

        System.out.println(newsServiceByMybatisImpl);
    }

    @Test
    public void testShowAllNews() {
        List<AllNews> allNews = newsdetailMapper.selectNewsList();
        System.out.println(allNews);
    }

    @Test
    public void testPageHelper() {
        PageHelper.startPage(2, 5);
        List<AllNews> allNews = newsdetailMapper.selectNewsList();
        allNews.forEach(System.out::println);
        PageInfo<AllNews> pageInfo = new PageInfo<>(allNews);
        System.out.println(pageInfo.getList());
    }

}
