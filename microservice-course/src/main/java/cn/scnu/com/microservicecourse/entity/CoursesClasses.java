package cn.scnu.com.microservicecourse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: tt
 * @Date: 2022/06/25 11:10
 */


@Data
@EqualsAndHashCode(callSuper = false)
public class CoursesClasses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    private Integer coursesId;

    /**
     * 课程名称
     */
    private String coursesName;

    /**
     * 课程性质(选修2必修1)
     */
    private String coursesType;

    /**
     * 学时
     */
    private Integer coursesHour;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 开课学期
     */
    private Integer semester;

    /**
     * 课程简介
     */
    private String coursesDescription;

    /**
     * 班级编号
     */
    private String classesId;

    /**
     * 上课第几时段
     */
    private Integer classTime;

    /**
     * 上课位置
     */
    private String classroom;

    /**
     * 课程可容纳人数
     */
    private Integer capacity;


    /**
     * 教学班授课老师名字
     */
    private String teacherName;

    /**
     * 星期几上课
     */
    private Integer classDay;

    /**
     * 剩余人数
     */
    private Integer number;

}
