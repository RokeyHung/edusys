package edusys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Manh Hung
 */
public class XDate {

    static SimpleDateFormat formatter = new SimpleDateFormat();

    /**
     * Chuyển đổi String sang Date
     *
     * @param date là String cần chuyển
     * @param pattern là định dạng thời gian
     * @return Date kết quả
     */
    public static Date toDate(String dateStr, String pattern) {
        try {
            formatter.applyPattern(pattern);
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Chuyển đổi từ Date sang String
     *
     * @param date là Date cần chuyển đổi
     * @param pattern là định dạng thời gian
     * @return String kết quả
     */
    public static String toString(Date date, String pattern) {
        formatter.applyPattern(pattern);
        return formatter.format(date);
    }

    /**
     * Bổ sung số ngày vào thời gian
     *
     * @param date thời gian hiện có
     * @param days số ngày cần bổ sung váo date
     * @return Date kết quả
     */

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static boolean isDate(String dateStr, String pattern) {
        try {
            formatter.applyPattern(pattern);
            formatter.setLenient(false);
            formatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
