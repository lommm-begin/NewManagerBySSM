package com.example.web.factory;

import com.example.web.service.Impl.NewsServiceByJdbcTemplateImpi;
import com.example.web.service.Impl.NewsServiceByMybatisImpl;
import com.example.web.service.NewsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
@PropertySource("classpath:dao.properties")
public class DataSourceFactory implements ApplicationContextAware {
    private ApplicationContext context;
    private Map<String, Supplier<NewsService>> newsServiceAll = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Autowired
    public void setDataSourceFactory(@Value("${Dao.myBatis}")String myBatis,
                                     @Value("${Dao.jdbcTemplate}")String jdbcTemplate) {

        newsServiceAll.put(myBatis, ()->this.context.getBean(NewsServiceByMybatisImpl.class));
        newsServiceAll.put(jdbcTemplate, ()->this.context.getBean(NewsServiceByJdbcTemplateImpi.class));
    }

    public NewsService getNewsService(String dataSource){
        Supplier<NewsService> newsServiceSupplier = newsServiceAll.get(dataSource);

        if (newsServiceSupplier == null){
            throw new IllegalStateException("参数不合法");
        }

        return newsServiceSupplier.get();
    }
}
