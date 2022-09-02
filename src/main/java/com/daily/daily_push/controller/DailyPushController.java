package com.daily.daily_push.controller;

import com.daily.daily_push.service.DailyPushUsersService;
import com.daily.daily_push.service.impl.DailyPushUsersImpl;
import com.daily.daily_push.service.impl.SendMessageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DailyPushController {

    @Resource
    private SendMessageImpl sendMessage;
    @Resource
    private DailyPushUsersService dailyPushUsers;

    /**
     * 推送文本卡片消息
     */
    @GetMapping("/sendTextcardMsg")
    public void sendTextcardMsg(){
        dailyPushUsers.sendTextcardMsg();
    }

    /**
     * 推送文本消息
     */
    @GetMapping("/sendTextMsg")
    public void sendTextMsg(){
        dailyPushUsers.sendTextMsg();
    }

    /**
     * 推送图文消息
     */
    @GetMapping("/sendNewsMsg")
    public void sendNewsMsg(){
        dailyPushUsers.sendNewsMsg();
    }



}
