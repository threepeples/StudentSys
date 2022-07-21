package cn.scnu.com.controller;

import cn.scnu.com.util.Result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CourseController
 * @Description
 * @date 2022/7/13 11:43
 * @Version 1.0
 * @Author HJW
 */
@RestController("course/manage/info")
public class CourseController {

    @RequestMapping("hello")
    public Result Hello(){
        return Result.success("{name: 张三}");
    }

}