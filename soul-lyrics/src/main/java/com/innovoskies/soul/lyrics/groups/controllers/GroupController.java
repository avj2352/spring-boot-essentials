package com.innovoskies.soul.lyrics.groups.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groups")
public class GroupController {

    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @GetMapping("hello")
    public String displayHello () {
        this.logger.info("Calling displayHello Get request");
        return "Hello World";
    }

}
