package cn.scnu.com.vo;

import lombok.Data;

/**
 * @ClassName OwnCurrentCourses
 * @Description
 * @date 2022/7/27 9:56
 * @Version 1.0
 * @Author HJW
 */
@Data
public class OwnCurrentCourses {
    private static final long serialVersionUID = 1L;

    //课程时间
    private int classTime;

    //课程名称
    private String className;

    //上课教室
    private String classroom;

    //老师名
    private String teacher;

    //上课星期
    private int classDay;

    //学分
    private int credit;
}