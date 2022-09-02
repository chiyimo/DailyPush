package com.daily.daily_push.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 天数计算工具类
 */
public class DateCalculation {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
    public static Integer after(String date) {
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    //恋爱日期
    public static Integer getRomanticDate(String romanticDate) {
        return after(romanticDate);
    }

    /**
     * 判断是否生日并计算年纪
     * @param birthday
     * @return
     * @throws ParseException
     */
    public static Integer getBirthday(String birthday) throws ParseException {
        //通过指定格式的SimpleDateFormat格式化Date对象，得到 月-日 （如：02-24）
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        int age = -1;
        if(today.substring(4).equals(birthday.substring(4))){

            Calendar cal = Calendar.getInstance();
            if (cal.before(birthday)) {
                return age;
            }
            //获取当前年度
            int yearNow = cal.get(Calendar.YEAR);
            cal.setTime(simpleDateFormat.parse(birthday));
            //获取出生年度
            int yearBirth = cal.get(Calendar.YEAR);
            //年相减
            age = yearNow - yearBirth;
        }
        return age;
    }

    /**
     *  计算距离生日还有多少天
     * @param birthday：生日日期
     */
    public static Integer CountBirthday(String birthday){
        Integer days = 0;
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String clidate = birthday;
            Calendar cToday = Calendar.getInstance(); // 存今天
            Calendar cBirth = Calendar.getInstance(); // 存生日
            cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
            cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
            if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
                // 生日已经过了，要算明年的了
                days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
                days += cBirth.get(Calendar.DAY_OF_YEAR);
            }else {
                // 生日还没过
                days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
}
