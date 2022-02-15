package com.lian.pet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Desc:
 * @Author: Lian
 * @Time: 2022/2/15 11:06
 */
@Slf4j
@RequestMapping("index")
@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping(value = "/login")
    public String test(){
        return "login.html";
    }
}
