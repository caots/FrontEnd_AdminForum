package com.bksoftware.controller.main.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeAdminController {

    private String token;

//    public String getToken(HttpServletRequest request) {
//        Cookie cookies[] = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            if (cookies[i].getName().equals("token")) {
//                token = cookies[i].getValue();
//            }
//        }
//
//        String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
//                .build().verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
//                .getSubject();
//
//        return username;
//    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String pageLogin(HttpServletRequest request) {
//        Cookie cookies[] = request.getCookies();
//        if (cookies!= null) {
//            for (int i = 0; i < cookies.length; i++) {
//                cookies[i].setMaxAge(0);
//            }
//        }
        return "login";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "homeAdmin";


    }

    //========================= News =================================


    @RequestMapping(value = {"/news"}, method = RequestMethod.GET)
    public String newsPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "news";
    }



    @RequestMapping(value = {"/tag"}, method = RequestMethod.GET)
    public String tagPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "tag";
    }

    //========================= Category =================================
    @GetMapping("/big-category")
    public String bigCategoryPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "bigCategory";
    }


    @GetMapping("/small-category")
    public String smallCategoryPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "smallCategory";
    }

    @GetMapping("/menu")
    public String MenuPage(HttpServletRequest request) {

//        String username = getToken(request);
//
//        if (username == null) {
//            return "login";
//        }
        return "menu";
    }



}
