package com.example.web.service.Impl;

import com.example.web.mapper.NewsdetailMapper;
import com.example.web.pojo.AllNews;
import com.example.web.pojo.UserMessage;
import com.example.web.service.NewsService;
import com.example.web.util.TableRowUtil;
import com.example.web.util.NewsTypeEnumUtil;
import com.example.web.util.TimeToStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceByMybatisImpl implements NewsService {

    @Autowired
    private NewsdetailMapper newsdetailMapper;

    @Override
    public List<AllNews> showAllNews() {
        List<AllNews> allNews = newsdetailMapper.selectNewsList();
        allNews.forEach(allNew -> {
            String newsTypeUtil = NewsTypeEnumUtil.getNewsTypeUtil(allNew.getType());
            allNew.setType(newsTypeUtil);

            if (allNew.getTime() != null) {
                String actualTime = TimeToStringUtil
                        .timestampToString(allNew.getTime());
                allNew.setActualTime(actualTime);
            }
        });

        return allNews;
    }

    @Override
    public AllNews showNewsById(int id) {
        AllNews allNews = newsdetailMapper.selectNewsById(id);

        if (allNews.getTime() != null) {
            String actualTime = TimeToStringUtil
                    .timestampToString(allNews.getTime());
            allNews.setActualTime(actualTime);
        }

        return allNews;
    }

    @Override
    public List<AllNews> selectByKeyword(String row, String keyword) {
        row = TableRowUtil.judgementRow(row);

        List<AllNews> allNews = newsdetailMapper.selectNewsByKeyword(row, keyword);
        allNews.forEach(allNew -> {
            if (allNew.getTime() != null) {
                String actualTime = TimeToStringUtil
                        .timestampToString(allNew.getTime());
                allNew.setActualTime(actualTime);
            }
        });

        return allNews;
    }

    @Override
    public boolean userLogin(String username, String password) {

        UserMessage userMessage = newsdetailMapper.selectUserByName(username, password);

        //null就返回false
        return userMessage != null;
    }

    @Override
    public boolean addNews(AllNews allNews) {
        int i = newsdetailMapper.selectNewsCountByNewsdetail();

        if (i <= Integer.MAX_VALUE) {
            return newsdetailMapper.insertNews(allNews) == 1;
        }

        return false;
    }

    @Override
    public boolean updateNews(AllNews allNews) {
        if (newsdetailMapper.selectNewsById(allNews.getId()) == null) {
            return false;
        }
        newsdetailMapper.updataNewsByPrimaryKey(allNews);

        return true;
    }

    @Override
    public boolean deleteNews(int id) {
        if (newsdetailMapper.selectNewsById(id) == null) {
            return false;
        }
        newsdetailMapper.deleteNewsByPrimaryKey(id);

        return true;
    }
}
