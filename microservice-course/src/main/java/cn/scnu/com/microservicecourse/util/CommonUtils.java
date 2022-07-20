package cn.scnu.com.microservicecourse.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 */
public class CommonUtils {

    public static Calendar BeginTime;
    public static Calendar EndTime;
    public static Timer timer=new Timer();;

    public static Date time;

    //判断现在是否是选课时间
    public static Boolean isSelectTime(){
        if (BeginTime==null||EndTime==null){
            return Boolean.FALSE;
        }
        if (Calendar.getInstance().after(BeginTime)){
            return Boolean.TRUE;//在之后，就可以开启
        }
        return Boolean.FALSE;
    }

    //关闭选课
    public static void closeSelect() {
        BeginTime=null;
        EndTime=null;
    }
    //设定选课
    public static void startSelect(Calendar calendar) {
        BeginTime=calendar;
        //设定写回程序的开始运行时间

    }

    public static void endSelect(Calendar calendar) {
        EndTime=calendar;
    }

}