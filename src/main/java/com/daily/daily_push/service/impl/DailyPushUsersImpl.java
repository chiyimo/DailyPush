package com.daily.daily_push.service.impl;

import com.daily.daily_push.service.DailyPushUsersService;
import com.daily.daily_push.service.SendMessageService;
import com.daily.daily_push.utils.DateCalculation;
import com.daily.daily_push.utils.TianApi;
import com.daily.daily_push.vo.ParameterListVo;
import com.daily.daily_push.vo.userList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

@Service
@Slf4j
public class DailyPushUsersImpl implements DailyPushUsersService {

    //彩虹屁key值
    @Value("${tianApi.tianApiKey}")
    private String tianApiKey;

    //星座
    @Value("${tianApi.XingZuoVlaue}")
    private String XingZuoVlaue;

    //天气地区
    @Value("${tianApi.WeatherVlaue}")
    private String WeatherVlaue;


    /**
     * 用户ID
     */
    @Value("${weixin.userIds}")
    private String userIds;

    /**
     * 生日
     */
    @Value("${target.birthday}")
    private String birthday;

    /**
     * 恋爱日期
     */
    @Value("${target.romanticDate}")
    private String romanticDate;

    @Resource
    private SendMessageService sendMessage;

    @Override
    public void sendTextcardMsg(){
        ParameterListVo parameterList = getParameterList();
        String title = "";


        //微信小表情网站：https://www.emojiall.com/zh-hans/platform-wechat
        StringBuffer message = new StringBuffer();
        if(parameterList.getCountBirthday() == 0){
            message.append("\uD83C\uDF82正如玫瑰逗人喜爱，愿你生日带给你愉快~\n");
        }
        //判断天气不为空
        if (parameterList.getWeatherVo() != null){
            //设置标题
            title = "\uD83C\uDF08早安~        " +parameterList.getWeatherVo().getDate() + " " + parameterList.getWeatherVo().getWeek();

            //消息内容，微信显示不了太多，不要弄太多了
            message.append("\uD83C\uDFF0城市：").append(parameterList.getWeatherVo().getArea());
            message.append("\n\uD83C\uDF25天气：").append(parameterList.getWeatherVo().getWeather());
            message.append("\n\uD83C\uDF21当前温度：").append(parameterList.getWeatherVo().getReal());
            message.append("\n\uD83E\uDD75最高温度：").append(parameterList.getWeatherVo().getHighest());
            message.append("\n\uD83E\uDD76最低温度：").append(parameterList.getWeatherVo().getLowest());
            message.append("\n☔︎降雨概率：").append(parameterList.getWeatherVo().getPop()).append("%");
            message.append("\n\uD83D\uDCA8风级：").append(parameterList.getWeatherVo().getWindsc());
//            message.append("\n\uD83C\uDF05日出时间：").append(parameterList.getWeatherVo().getSunrise());
//            message.append("\n\uD83C\uDF07日落时间：").append(parameterList.getWeatherVo().getSunset());
            message.append("\n\uD83C\uDFD6生活指数：").append(parameterList.getWeatherVo().getTips());
        }

        //判断土味情话不为空
//        if (StringUtils.isNotEmpty(parameterList.getSaylove())){
//            message.append("\n土味情话：").append(parameterList.getSaylove());
//        }
        message.append("\n\uD83C\uDF82距离你下个生日还有").append(parameterList.getCountBirthday()).append("天噢~");

        if (parameterList.getCountRomanticDate() == 0){
            //判断是否恋爱当天
            message.append("\n\uD83D\uDC91谈恋爱真的很麻烦，所以以后就麻烦你啦~");
        }else{
            message.append("\n\uD83D\uDC91今天是我们恋爱的第").append(parameterList.getCountRomanticDate()).append("天噢~");
        }

        //判断彩虹屁不为空
        if (StringUtils.isNotEmpty(parameterList.getCaiHongPi())){
            message.append("\n\n\uD83D\uDC8C").append(parameterList.getCaiHongPi());
        }

        //设置卡片跳转链接
        String url = "https://www.yuque.com/docs/share/00269daa-1dcb-402a-bb2a-9f838984443d?# 《企业微信每日早安推送操作流程》";

        //循环推送多个用户
        sendMessage.sendTextcardMsg(userIds,title,message.toString(),url);
    }

    @Override
    public void sendTextMsg() {
        String message = "测试消息";
        sendMessage.sendTextMsg(userIds.toString(),message);
    }

    @Override
    public void sendNewsMsg() {
        //设置卡片跳转链接
        String url = "https://www.yuque.com/docs/share/00269daa-1dcb-402a-bb2a-9f838984443d?# 《企业微信每日早安推送操作流程》";
        String imgUrl = "https://wechat-push.oss-cn-hangzhou.aliyuncs.com/%E5%96%9D%E6%B0%B4%E5%B0%8F%E5%8A%A9%E6%89%8B.jpg";
        sendMessage.sendNewsMsg(userIds, "喝水提醒", "温馨提示：适量喝水可以促进新陈代谢，润泽皮肤和咽喉噢~", url, imgUrl);
    }



    private ParameterListVo getParameterList(){
        ParameterListVo parameterList = new ParameterListVo();
        if (StringUtils.isNotEmpty(tianApiKey) && StringUtils.isNotEmpty(WeatherVlaue)){
            //获取天气信息
            parameterList.setWeatherVo(TianApi.getWeather(tianApiKey, WeatherVlaue));
        }

        if (StringUtils.isNotEmpty(tianApiKey)){
            //获取彩虹屁
            parameterList.setCaiHongPi(TianApi.getCaiHongPi(tianApiKey));
            //获取土味情话
            parameterList.setSaylove(TianApi.getSaylove(tianApiKey));
        }


        if (StringUtils.isNotEmpty(tianApiKey) && StringUtils.isNotEmpty(XingZuoVlaue)){
            //获取星座信息
            parameterList.setXingZuo(TianApi.getXingZuo(tianApiKey, XingZuoVlaue));
            log.info(parameterList.getXingZuo().getTitle());
        }


        if (StringUtils.isNotEmpty(birthday)){
            //计算离下个生日的天数
            parameterList.setCountBirthday(DateCalculation.CountBirthday(birthday));
        }


        if (StringUtils.isNotEmpty(romanticDate)){
            //计算恋爱天数
            parameterList.setCountRomanticDate(DateCalculation.getRomanticDate(romanticDate));
        }

        return parameterList;
    }

}
