package com.daily.daily_push.service;

public interface DailyPushUsersService {
    /**
     * 推送文本卡片消息
     */
    void sendTextcardMsg();

    /**
     * 推送文字消息
     */
    void sendTextMsg();

    /**
     * 图文消息
     */
    void sendNewsMsg();
}
