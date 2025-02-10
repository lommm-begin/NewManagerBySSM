package com.example.web.controller;

import com.example.web.pojo.AllNews;
import com.example.web.service.NewsService;
import com.example.web.util.TimeToStringUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class NewsAdminController {

    @Autowired
    @Qualifier("newsServiceByMybatisImpl")
    private NewsService newsService;

    /**
     * 搜索内容
     * @param model
     * @param keyword
     * @param row
     * @return
     */
    @GetMapping("/Search")
    public String showNew(ModelMap model,
                          @RequestParam("keyword") String keyword,
                          @RequestParam("row") String row) {

        List<AllNews> allNews = newsService.selectByKeyword(row, keyword);

        model.addAttribute("allist", allNews);

        //在搜索页保留输入
        model.addAttribute("keyword", keyword);
        model.addAttribute("row", row);

        return "admin/adminSearch";
    }

    /**
     * 进入管理员首页
     * @param model
     * @return
     */
    @GetMapping("/main")
    public String main(ModelMap model) {
        List<AllNews> allNews = newsService.showAllNews();
        model.addAttribute("allist", allNews);

        return "admin/main";
    }

    /**
     * 跳转到增加新闻页面
     * @param model
     * @return
     */
    @GetMapping("/addNew")
    public String addNew(ModelMap model) {

        String nowTime = TimeToStringUtil.getNowTime();

        model.addAttribute("nowTime", nowTime);

        return "admin/addNew";
    }

    @PostMapping("/CommitNew")
    public String commitNew(AllNews allNews) {

        try {
            newsService.addNews(allNews);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect: /admin/main";
    }

    /**
     * 获取点击的内容进行展示
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editNew")
    public String editNew(@RequestParam("id") int id,
                          ModelMap model) {

        AllNews allNews = newsService.showNewsById(id);
        model.addAttribute("allist", allNews);

        return "admin/editNew";
    }

    /**
     * 提交修改内容
     * @param allNews
     * @return
     */
    @PutMapping("/CommitUpdateNew")
    public String commitUpdataNew(AllNews allNews) {
        newsService.updateNews(allNews);
        System.out.println(allNews);
        return "redirect:/admin/main";
    }

    /**
     * 删除对应id的新闻
     * @param id
     * @return
     */
    @DeleteMapping("/deleteNew/{id}")
    public String deleteNew(@PathVariable("id") int id) {
        newsService.deleteNews(id);

        return "redirect:/admin/main";
    }

    @GetMapping("/exit")
    public String exit(HttpServletRequest request, HttpServletResponse response) {
        Cookie loginCookie = new Cookie("username", null);
        loginCookie.setMaxAge(-1);
        loginCookie.setPath("/");
        response.addCookie(loginCookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
