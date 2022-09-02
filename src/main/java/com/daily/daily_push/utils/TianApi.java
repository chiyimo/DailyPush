package com.daily.daily_push.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daily.daily_push.vo.WeatherVo;
import com.daily.daily_push.vo.XingZuoVo;

import java.io.IOException;

/**
 * 金句类
 */
public class TianApi {


    /**
     * 彩虹屁
     * @param key
     * @return
     */
    public static String getCaiHongPi(String key) {
        String url = "http://api.tianapi.com/caihongpi/index?key=";
        String str = "阳光落在屋里，爱你藏在心里";
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url+key));
            if (jsonObject.getIntValue("code") == 200) {
                //转换返回json数据
                str = jsonObject.getJSONArray("newslist").getJSONObject(0).getString("content");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 星座
     * @param key
     * @return
     */
    public static XingZuoVo getXingZuo(String key,String me) {
        String url = "http://api.tianapi.com/xingzuo/index?key=";
        XingZuoVo xingZuoVo = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url+key+"&me="+me));
            if (jsonObject.getIntValue("code") == 200) {
                //转换返回json数据
                xingZuoVo = JSON.parseObject(jsonObject.getJSONArray("newslist").getJSONObject(0).toString(),XingZuoVo.class);
//                xingZuoVo.setTitle(jsonObject.getJSONArray("newslist").getJSONObject(0).getString("title"));
//                xingZuoVo.setGrade(jsonObject.getJSONArray("newslist").getJSONObject(0).getString("grade"));
//                xingZuoVo.setContent(jsonObject.getJSONArray("newslist").getJSONObject(0).getString("content"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xingZuoVo;
    }


    /**
     * 土味情话
     * @param key
     * @return
     */
    public static String getSaylove(String key) {
        String url = "http://api.tianapi.com/saylove/index?key=";
        String str = "你是我夜里的星星千万颗，闪烁着心花朵朵。";
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url+key));
            if (jsonObject.getIntValue("code") == 200) {
                //转换返回json数据
                str = jsonObject.getJSONArray("newslist").getJSONObject(0).getString("content");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 天气
     * @param key
     * @return
     */
    public static WeatherVo getWeather(String key, String city) {
        String url = "http://api.tianapi.com/tianqi/index?key=";
        WeatherVo weatherVo = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url+key+"&city="+city));
            if (jsonObject.getIntValue("code") == 200) {
                //转换返回json数据
                weatherVo = JSON.parseObject(jsonObject.getJSONArray("newslist").getJSONObject(0).toString(),WeatherVo.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherVo;
    }


}
