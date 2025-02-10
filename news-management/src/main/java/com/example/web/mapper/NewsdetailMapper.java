package com.example.web.mapper;

import com.example.web.pojo.AllNews;
import com.example.web.pojo.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsdetailMapper {

    AllNews selectNewsById(@Param("id") Integer id);

    List<AllNews> selectNewsList();

    List<AllNews> selectNewsByKeyword(@Param("row") String row, @Param("keyword") String keyword);

    UserMessage selectUserByName(@Param("username") String username, @Param("password") String password);

    int selectNewsCountByNewsdetail();

    int insertNews(AllNews news);

    UserMessage selectUserMessageByUsername(@Param("username") String username);

    int updataNewsByPrimaryKey(AllNews news);

    int deleteNewsByPrimaryKey(Integer id);
}
