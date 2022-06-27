package com.jiawa.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    @Value("${test.hello}")
    private String hello;

    /**
     * GET POST PUT DELETE
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return this.hello;
    }
}
