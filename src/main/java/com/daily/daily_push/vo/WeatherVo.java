package com.daily.daily_push.vo;

import lombok.Data;

/**
 * 天气返回参数
 */
@Data
public class WeatherVo {
    /**
     * 地区
     */
    private String area;
    /**
     * 日期
     */
    private String date;
    /**
     * 星期
     */
    private String week;
    /**
     * 早晚天气变化
     */
    private String weather;
    /**
     * 实时天气
     */
    private String real;
    /**
     *最低天气
     */
    private String lowest;
    /**
     * 最高天气
     */
    private String highest;
    /**
     * 风向
     */
    private String wind;
    /**
     * 风级
     */
    private String windsc;
    /**
     * 日出时间
     */
    private String sunrise;
    /**
     * 日落时间
     */
    private String sunset;
    /**
     * 降雨概率
     */
    private String pop;
    /**
     * 紫外线强度
     */
    private String uv_index;
    /**
     * 生活指数
     */
    private String tips;


}
