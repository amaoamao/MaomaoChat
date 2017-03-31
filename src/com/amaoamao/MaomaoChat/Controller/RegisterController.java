package com.amaoamao.MaomaoChat.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-3-6.
 */
@RestController
@RequestMapping("/api")
public class RegisterController {
    @RequestMapping(value = "/{phoneNumber}", method = RequestMethod.POST)
    public String register(@PathVariable("phoneNumber") Long phoneNumber, String authCode) {
        return phoneNumber + authCode;
    }

    @RequestMapping(value = "/{phoneNumber}", method = RequestMethod.GET)
    public String registerr(@PathVariable("phoneNumber") Long phoneNumber, String authCode) {
        return phoneNumber + authCode;
    }
}
