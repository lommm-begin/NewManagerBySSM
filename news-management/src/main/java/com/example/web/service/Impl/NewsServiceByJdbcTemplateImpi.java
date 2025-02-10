package com.example.web.service.Impl;

import com.example.web.pojo.AllNews;
import com.example.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceByJdbcTemplateImpi implements NewsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AllNews> showAllNews() {
        String sql = "select * from news";
        List<AllNews> allNews = jdbcTemplate.query(sql, new BeanPropertyRowMapper(AllNews.class));
        return allNews;
    }

    @Override
    public AllNews showNewsById(int id) {
        return null;
    }

    @Override
    public List<AllNews> selectByKeyword(String row, String keyword) {
        return null;
    }

    @Override
    public boolean userLogin(String username, String password) {

        return true;
    }

    @Override
    public boolean addNews(AllNews allNews) {
        return false;
    }

    @Override
    public boolean deleteNews(int id) {
        return false;
    }

    @Override
    public boolean updateNews(AllNews allNews) {
        return false;
    }
}
