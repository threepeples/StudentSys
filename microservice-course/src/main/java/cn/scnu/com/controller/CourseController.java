package cn.scnu.com.controller;

import cn.scnu.com.pojo.Courses;
import cn.scnu.com.service.CourseService;
import cn.scnu.com.util.EasyUIResult;
import cn.scnu.com.util.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CourseController
 * @Description
 * @date 2022/7/13 11:43
 * @Version 1.0
 * @Author HJW
 */
@RestController
@RequestMapping("/course/manage/info")
public class CourseController {
    @Autowired
    private CourseService courseService;
    //分页显示，传输方式get，url参数:pageManage
    @GetMapping("pageManage")
    public Result coursesPageQuery(Integer page,Integer rows){
        EasyUIResult result=courseService.courseByPage(page, rows);
        return Result.success(result);
    }
    //通过id查看课程情况，传输方式get，url参数:item,参数:courseId（String）
    @GetMapping("item/{courses_id}")
    public Result queryById(@PathVariable("courses_id")Integer courses_id){
        try {
           Courses courses=courseService.queryById(courses_id);
            //没有异常返回成功信息
            return Result.success(courseService.queryById(courses_id));

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
            courseService.CoursesSave(courses);
            //没有异常返回成功信息
            return Result.success("商品新增成功!");
        }catch (Exception e){
            //有异常返回失败信息
            e.printStackTrace();
            return Result.error("商品新增失败"+"失败原因:"+e.getMessage());
        }
    }
    //商品更新,传输方法Get，url参数：update
    @GetMapping("update")
    public Result coursesUpdate(Courses courses){
        //使用异常信息来表示成功和失败
        try {
            courseService.CourseUpdate(courses);
            //没有异常返回成功信息
            return Result.success("商品更新成功!");
        }catch (Exception e) {
            //有异常返回异常信息
            e.printStackTrace();
            return Result.error("商品更新失败！"+"失败原因:"+e.getMessage());
        }
    }


}