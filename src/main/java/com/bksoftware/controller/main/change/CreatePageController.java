package com.bksoftware.controller.main.change;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CreatePageController {

//    public String getToken(HttpServletRequest request) {
//        HomeAdminController homeAdminController = new HomeAdminController();
//        String token = homeAdminController.getToken(request);
//        return token;
//    }

    //=========================Category=================================
    @GetMapping("/create-category")
    public String createCategoryPage(HttpServletRequest request) {

        return "createCategory";
    }

    @GetMapping("/update-category")
    public String updateCategoryPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {

        return "updateCategory";
    }

    @GetMapping("/create-tag")
    public String createTagPage(HttpServletRequest request) {
        return "createTag";
    }

    @GetMapping("/update-tag")
    public String updateTagPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        return "updateTag";
    }

}