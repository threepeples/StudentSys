package cn.scnu.com.vo;

import lombok.Data;

/**
 * @ClassName OwnGrade
 * @Description
 * @date 2022/7/27 10:00
 * @Version 1.0
 * @Author HJW
 */
@Data
public class OwnGrade {
    private static final long serialVersionUID = 1L;

    //课程名称
    private String className;

    //学分
    private double credit;

    //成绩
    private double grade;

    //课时
    private String coursesHour;

    //课程编号
    private int coursesId;
}