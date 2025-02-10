package com.example.web.service;

import com.example.web.pojo.AllNews;

import java.util.List;

public interface NewsService {
    /**
     * 查询所有内容
     * @return 所有新闻
     */
    public List<AllNews> showAllNews();

    /**
     * 根据主键查询内容
     * @param id
     * @return
     */
    public AllNews showNewsById(int id);

    /**
     * 根据关键字模糊查询
     * @param keyword
     * @return
     */
    public List<AllNews> selectByKeyword(String row, String keyword);

    /**
     * 用户登录注册
     * @param username
     * @param password
     * @return
     */
    public boolean userLogin(String username, String password);

    /**
     * 添加新闻
     * @param allNews
     * @return
     */
    public boolean addNews(AllNews allNews);

    /**
     * 更新新闻
     * @param allNews
     * @return
     */
    public boolean updateNews(AllNews allNews);

    /**
     * 删除新闻
     * @param id
     * @return
     */
    public boolean deleteNews(int id);
}
