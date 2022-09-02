package com.daily.daily_push.service.impl;

import com.daily.daily_push.service.SendMessageService;
import com.daily.daily_push.utils.DateCalculation;
import com.daily.daily_push.utils.TianApi;
import com.daily.daily_push.config.WxConfig;
import com.daily.daily_push.vo.WeatherVo;
import com.daily.daily_push.vo.XingZuoVo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速构建并发送消息类
 */
@Service
@Slf4j
public class SendMessageImpl implements SendMessageService {

    @Override
    public void sendTextcardMsg(String userId, String title, String message, String url) {
        try{
            //微信消息对象
            WxCpMessageServiceImpl wxCpMessageService = new WxCpMessageServiceImpl(WxConfig.getWxCpService());
            WxCpMessage textcardMsg = new WxCpMessage();
            textcardMsg.setSafe("0");
            //设置消息类型
            textcardMsg.setMsgType("textcard");
            //设置发送用户
            textcardMsg.setToUser(userId);
            //发送的标题
            textcardMsg.setTitle(title);
//          wxCpMessage.setTitle("\uD83C\uDF03晚安~      " +weather.getDate() + " " + weather.getWeek());
            //发送内容
            textcardMsg.setDescription(message);
            //设置跳转；可以自己制作一个网页
            textcardMsg.setUrl(url);
            textcardMsg.setBtnTxt("详情");
            wxCpMessageService.send(textcardMsg);
            log.info(textcardMsg.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendTextMsg(String userId,String message){
        try {
            WxCpMessageServiceImpl wxCpMessageService = new WxCpMessageServiceImpl(WxConfig.getWxCpService());
            WxCpMessage textMsg = new WxCpMessage();
            textMsg.setSafe("0");
            //设置消息类型
            textMsg.setMsgType("text");
            //设置发送用户
            textMsg.setToUser(userId);
            textMsg.setContent(message);
            wxCpMessageService.send(textMsg);
            log.info(textMsg.toString());
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNewsMsg(String userId, String title, String message, String url, String imgUrl) {
        try{
            //微信消息对象
            WxCpMessageServiceImpl wxCpMessageService = new WxCpMessageServiceImpl(WxConfig.getWxCpService());
            WxCpMessage newsMsg = new WxCpMessage();
            newsMsg.setSafe("0");
            //设置消息类型
            newsMsg.setMsgType("news");
            //设置发送用户
            newsMsg.setToUser(userId);

            List<NewArticle> articlesList = new ArrayList<>();
            NewArticle newArticle = new NewArticle();
            //发送的标题
            newArticle.setTitle(title);
            //按钮文本
            newArticle.setBtnText("详情");
            //发送内容
            newArticle.setDescription(message);
            //图片地址
            newArticle.setPicUrl(imgUrl);
            //设置跳转；可以自己制作一个网页
            newArticle.setUrl(url);
            //添加到List集合
            articlesList.add(newArticle);
            newsMsg.setArticles(articlesList);

            wxCpMessageService.send(newsMsg);

            log.info(newsMsg.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
