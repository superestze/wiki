package com.jiawa.wiki.controller;

import com.jiawa.wiki.domain.Test;
import com.jiawa.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TextController {
    @Resource
    private TestService testService;

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

    @RequestMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
