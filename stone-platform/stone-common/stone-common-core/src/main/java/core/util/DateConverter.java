package core.util;


import cn.hutool.core.date.DatePattern;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zfq
 * @version 1.0
 * @Description
 * @date 2020年05月13日　12:04
 */
public class DateConverter {
    public static Date convert(String source) {
        source = source.trim();
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, DatePattern.NORM_DATE_PATTERN);
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, DatePattern.NORM_DATETIME_PATTERN);
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        Date date=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 格式化日期
     * @param date 日期
     * @param format String 格式
     * @return Date 日期
     */
    public static String date2String(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
