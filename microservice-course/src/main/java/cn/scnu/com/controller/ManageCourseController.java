package cn.scnu.com.controller;

import cn.scnu.com.pojo.Courses;
import cn.scnu.com.service.ICoursesService;
import cn.scnu.com.util.ApplicationContextUtil;
import cn.scnu.com.util.CommonUtils;

import cn.scnu.com.util.EasyUIResult;
import cn.scnu.com.util.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 用于进行选课管理的接口
 * 开启数据预热，选课过程定期进行数据读入等
 * @author: tt
 * @Date: 2022/06/27 16:35
 */

@RestController
@EnableScheduling
@RequestMapping("/course/manage")
public class ManageCourseController {

    @Autowired
    private ICoursesService coursesService;

    private static Timer timer=new Timer();

    //开始选课，输入选课时间
    @PostMapping("startSelectTime")
    public Result startSelect(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second){
        LocalDateTime localDateTime =null;
        try {
            localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        }catch (java.time.DateTimeException e){
            return  Result.error("输入时间参数有误："+e.getMessage());
        }catch (NullPointerException e){
            return  Result.error("输入时间参数存在空值");
        }

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, localDateTime.get(ChronoField.YEAR)); // 设置年份
        calendar.set(Calendar.DAY_OF_MONTH, localDateTime.get(ChronoField.DAY_OF_MONTH));//设置日期
        calendar.set(Calendar.MONTH, localDateTime.get(ChronoField.MONTH_OF_YEAR)-1);//设置日期为月份   这里3表示4月份    4就表示5月份
        calendar.set(Calendar.HOUR_OF_DAY, localDateTime.get(ChronoField.HOUR_OF_DAY)); //设置触发时
        calendar.set(Calendar.MINUTE, localDateTime.get(ChronoField.MINUTE_OF_HOUR)); //设置触发分
        calendar.set(Calendar.SECOND, localDateTime.get(ChronoField.SECOND_OF_MINUTE)); //设置触发秒

        //存储设定好的选课时间
        CommonUtils.startSelect(calendar);
        coursesService.startSelect(calendar);


        return  Result.success("设定选课开始时间成功");
    }

    //结束选课时间，输入结束选课时间
    @PostMapping("endSelectTime")
    public Result endSelectTime(Integer year,Integer month,Integer day,Integer hour,Integer minute,Integer second){


        LocalDateTime localDateTime =null;
        try {
            localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        }catch (java.time.DateTimeException e){
            return  Result.error("输入时间参数有误："+e.getMessage());
        }

        Calendar endTime = Calendar.getInstance();

        endTime.set(Calendar.YEAR, localDateTime.get(ChronoField.YEAR)); // 设置年份
        endTime.set(Calendar.DAY_OF_MONTH, localDateTime.get(ChronoField.DAY_OF_MONTH));//设置日期
        endTime.set(Calendar.MONTH, localDateTime.get(ChronoField.MONTH_OF_YEAR)-1);//设置日期为月份   这里3表示4月份    4就表示5月份
        endTime.set(Calendar.HOUR_OF_DAY, localDateTime.get(ChronoField.HOUR_OF_DAY)); //设置触发时
        endTime.set(Calendar.MINUTE, localDateTime.get(ChronoField.MINUTE_OF_HOUR)); //设置触发分
        endTime.set(Calendar.SECOND, localDateTime.get(ChronoField.SECOND_OF_MINUTE)); //设置触发秒

        //存储设定好的选课时间
        CommonUtils.endSelect(endTime);
        timer.cancel();
        timer=new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               //System.out.println("结束选课");
                               ICoursesService coursesServicein = (ICoursesService) ApplicationContextUtil.getBean("ManageService");
                               coursesServicein.closeSelect();
                               //结束时需要最后一次写回操作（全写回）
                               coursesServicein.writebackRedisAll();
                           }
                       },endTime.getTime());

        return  Result.success("设定选课结束时间成功");
    }

    //关闭选课，选课时间
    @PostMapping("closeSelect")
    public Result startSelect(){
        CommonUtils.closeSelect();
        coursesService.closeSelect();
        //立刻进行全写回

        coursesService.writebackRedisAll();

        return  Result.success("关闭选课成功");
    }

    //查看选课时间,只有到了选课时间才能进行选课
    @GetMapping("isSelectTime")
    public Result isSelectTime(){

        return  Result.success(CommonUtils.isSelectTime());
    }
    //查看选课时间详细部署
    @GetMapping("getSelectTime")
    public Result getSelectTime(){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 设置你想要的格式

        return  Result.success(df.format(CommonUtils.BeginTime.getTime())+","+df.format(CommonUtils.EndTime.getTime()));
    }






    @GetMapping("pageManage")
    public Result coursesPageQuery(Integer page,Integer rows){
        EasyUIResult result=coursesService.queryByPage(page, rows);
        System.out.println(result.toString());
        return Result.success(result);
    }
    //通过id查看课程情况，传输方式get，url参数:item,参数:courseId（String）
    @GetMapping("item/{courses_id}")
    public Result queryById(@PathVariable Integer courses_id){
        try {
            //没有异常返回成功信息
            return Result.success(coursesService.queryById(courses_id));

        }catch (Exception e){
            //有异常返回失败信息
            e.printStackTrace();
            return Result.error("课程查看失败"+"失败原因:"+e.getMessage());
        }
    }
    //商品新增，传输方式：Get，url:参数save
    @GetMapping("save")
    public Result courseSave(Courses courses){
        //使用异常信息来表示成功和失败
        try {
            coursesService.CoursesSave(courses);
            //没有异常返回成功信息
            return Result.success("课程新增成功!");
        }catch (Exception e){
            //有异常返回失败信息
            e.printStackTrace();
            return Result.error("课程新增失败"+"失败原因:"+e.getMessage());
        }
    }
    //商品更新,传输方法Get，url参数：update
    @GetMapping("update")
    public Result coursesUpdate(Courses courses){
        //使用异常信息来表示成功和失败
        try {
            coursesService.CourseUpdate(courses);
            //没有异常返回成功信息
            return Result.success("课程更新成功!");
        }catch (Exception e) {
            //有异常返回异常信息
            e.printStackTrace();
            return Result.error("课程更新失败！"+"失败原因:"+e.getMessage());
        }
    }
    //删除课程
    @GetMapping("delete/{courses_id}")
    public Result coursesDelete(@PathVariable Integer courses_id){
        try {
            coursesService.CourseDelete(courses_id);
            return Result.success("课程删除成功！");
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！"+"失败原因:"+e.getMessage());
        }
    }


}
