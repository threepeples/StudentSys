package cn.scnu.com.controller;

import cn.scnu.com.service.impl.CoursesServiceImpl;
import cn.scnu.com.util.CommonUtils;
import cn.scnu.com.pojo.CoursesClasses;
import cn.scnu.com.util.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 课程大类信息表 前端控制器
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */

@RestController
@EnableScheduling
@RequestMapping("/course/courses")
@CrossOrigin
public class CoursesController {
    @Autowired
    private CoursesServiceImpl coursesService;

    //获取学生对应年级的可选课程列表，整个返回
    @GetMapping("selectableAll")
    public Result getSelectableAll(String majors_id, Integer semester){

        if ("".equals(majors_id)){
            return Result.error("专业id不能为空");
        }
        if (majors_id==null||semester==null){
            return Result.error("参数不能为空");
        }

        if (!CommonUtils.isSelectTime()){
            return Result.error("不是选课时间");
        }


        List<CoursesClasses> selectableClasses;

        selectableClasses=coursesService.getSelectableClasses(majors_id,semester);
        if (selectableClasses==null){
            return Result.error("没有相应专业，年级可选课程列表，请确认所给参数是否正确");
        }



        return  Result.success(selectableClasses);
    }

    //学生看自己已经选择了什么课(详细信息)
    @GetMapping("selected")
    public Result getSelectableClassesInfo(Integer student_id){


        //需要保证传入student_id有效————通过学生

        if (student_id==null){
            return Result.error("student_id参数不能为空");
        }

        //获得学生已选列表id
        Set<String> selectableClassesId=coursesService.getSelectedClassId(student_id);

        if (selectableClassesId.isEmpty()){
            return Result.error("学生没有选择任何课程课");
        }


        List<CoursesClasses> selectableClassesInfo = new ArrayList<CoursesClasses>();
        //循环可选列表进行加载
        for (String classes_id:
                selectableClassesId) {
            //通过classes_id获取class详细信息
            selectableClassesInfo.add(coursesService.getClassesInfo(classes_id));
        }

        return  Result.success(selectableClassesInfo);
    }


    //学生选择某个课程的接口
    @PostMapping("joinClass")
    public Result getJoinClass(Integer student_id,String classes_id,String majors_id, Integer semester){
        if ("".equals(classes_id)||"".equals(majors_id)){
            return Result.error("接口参数不能为空");
        }
        if (student_id==null||classes_id==null||majors_id==null||semester==null){
            return Result.error("参数不能为空");
        }


        if (!CommonUtils.isSelectTime()){
            return Result.error("不是选课时间");
        }


        //判断剩余人数
        if (coursesService.getClassNumberLeft(classes_id)<1){

            return  Result.error("选课失败,人数已满");
        }
        //判断是否已选
        if(coursesService.getSelectedOne(student_id,classes_id)) {
            return  Result.error("选课失败,已选该课程");
        }
        //判断是否可选
        if(!coursesService.isSelectable(classes_id,majors_id,semester)) {
            return  Result.error("选课失败,请确认参数输入是否正确");
        }
        //进行选课
        coursesService.joinClass(student_id, classes_id);
        return  Result.success("选课成功");
    }

    //学生退选某个课程的接口
    @PostMapping("dropClass")
    public Result getDropClass(Integer student_id,String classes_id){



        if (student_id==null||classes_id==null){
            return Result.error("参数不能为空");
        }


        if (!CommonUtils.isSelectTime()){
            return Result.error("不是选课时间");
        }

        //1.是否选了
        //同时也能保证studentid的合法性
        if(!coursesService.getSelectedOneClass(student_id,classes_id)){
            //没有加入该课程
            return  Result.error("退选失败,没有选择该课程或者id参数错误");
        }

        //进行退选
        coursesService.dropClass(student_id, classes_id);
        return  Result.success("退选成功");
    }


    //通过传进来的课程id，获得选课班的剩余人数的接口
    @GetMapping("ClassNumber")
    public Result getClassNumber(String classes_id){

        if ("".equals(classes_id)){
            return Result.error("接口参数不能为空");
        }
        if (classes_id==null){
            return Result.error("接口参数不能为空");
        }


        if (!CommonUtils.isSelectTime()){
            return Result.error("不是选课时间");
        }


        Integer number=coursesService.getClassNumberLeft(classes_id);

        if (number==null){
            return Result.error("请查看所给课程id是否正确，数据库内没有该课程");
        }
        return  Result.success(number);
    }

}
