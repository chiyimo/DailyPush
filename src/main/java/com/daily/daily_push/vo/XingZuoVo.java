package com.daily.daily_push.vo;

import lombok.Data;

/**
 * 星座返回参数
 */
@Data
public class XingZuoVo {
    /**
     * 星座
     */
    private String title;

    /**
     * 点评
     */
    private String grade;

    /**
     * 内容
     */
    private String content;

}
