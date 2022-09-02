package com.daily.daily_push.service;

/**
 * 快速构建并发送消息类
 */
public interface SendMessageService {

    /**
     * 文字卡片消息
     * @param userId 推送目标企业微信ID
     * @param title 推送卡片标题
     * @param message 消息内容
     * @param url URL地址
     */
    void sendTextcardMsg(String userId, String title, String message, String url);

    /**
     * 推送文本消息
     * @param userId
     * @param message
     */
    void sendTextMsg(String userId,String message);

    /**
     * 推送图文消息
     * @param userId
     * @param title
     * @param message
     * @param url
     * @param imgUrl
     */
    void sendNewsMsg(String userId, String title, String message, String url, String imgUrl);
}
