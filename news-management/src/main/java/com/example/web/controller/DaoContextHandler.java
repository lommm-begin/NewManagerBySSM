package com.example.web.controller;

import com.example.web.factory.DataSourceFactory;
import com.example.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoContextHandler {

    private NewsService newsService;

    private  DataSourceFactory dataSourceFactory;


    @Autowired
    public void DataSourceContextService(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public DaoContextHandler setNewsService(String dataByDaoName) {
        try {
            this.newsService = dataSourceFactory.getNewsService(dataByDaoName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return this;
    }

    public NewsService getNewsService() {
        return this.newsService;
    }
}
