package com.example.web.controller;


import com.example.web.pojo.AllNews;
import com.example.web.pojo.UserMessage;
import com.example.web.service.NewsService;
import com.example.web.util.encryption.EncryptionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"adminname"})
public class NewsUserController {

    @Autowired
    @Qualifier("newsServiceByMybatisImpl")
    private NewsService newsService;

    /**
     * 新闻细节页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id,
                         ModelMap model) {
        AllNews allNews = newsService.showNewsById(id);

        model.addAttribute("allist", allNews);
        model.addAttribute("id", allNews.getId());

        return "user/detail";
    }

    /**
     * 所有新闻页面
     * @param model
     * @return
     */
    @GetMapping("/allNew")
    public String allNew(ModelMap model) {
        List<AllNews> allNews = newsService.showAllNews();

        model.addAttribute("allist", allNews);

        return "user/allNew";
    }

    /**
     * 登录验证
     * @param userMessage
     * @param response
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/loginUser")
    public String login(UserMessage userMessage,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes,
                        ModelMap model) {

        //设置用户名和密码
        String username = userMessage.getUsername();
        String PasswordField = userMessage.getPassword();
        System.out.println(username);

        try {
           if (username != null && PasswordField != null) {
               if (newsService.userLogin(username, PasswordField)) {
                   //登录成功后显示名称
                   //session作用域
                   model.addAttribute("adminname", username);

                   //加密
                   username = EncryptionUtil.encryptUserName(username);

                   //设置cookie
                   Cookie cookie = new Cookie("username", username);
                   cookie.setPath("/");
                   cookie.setHttpOnly(true);
                   cookie.setSecure(true);
                   cookie.setMaxAge(60 * 60 * 24);
                   response.addCookie(cookie);

                   return "redirect:/admin/main";
               }
           }
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", PasswordField);
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");

           return "redirect:/user/login";

       } catch (Exception e) {
           e.printStackTrace();
           redirectAttributes.addFlashAttribute("error", "服务内部错误");
           return "redirect:/user/login";
       }
    }
}
