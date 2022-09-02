package com.daily.daily_push.vo;

import lombok.Data;

@Data
public class ParameterListVo {
    /**
     * 天气参数
     */
    private WeatherVo weatherVo;

    /**
     * 彩虹屁
     */
    private String caiHongPi;

    /**
     * 土味情话
     */
    private String saylove;

    /**
     * 星座
     */
    private XingZuoVo xingZuo;

    /**
     * 计算生日天数
     */
    private Integer countBirthday;

    /**
     * 计算恋爱天数
     */
    private Integer countRomanticDate;
}
