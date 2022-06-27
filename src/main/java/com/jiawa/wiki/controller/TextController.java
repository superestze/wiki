package com.jiawa.wiki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    /**
     * GET POST PUT DELETE
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
