package cn.scnu.com.pojo;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "课程编号")
    private Integer coursesId;

    @ApiModelProperty(value = "课程名称")
    private String coursesName;

    @ApiModelProperty(value = "课程性质")
    /*(选修2必修1)*/
    private String coursesType;

    @ApiModelProperty(value = "学时")
    private Integer coursesHour;

    @ApiModelProperty(value = "学分")
    private Integer credit;

    @ApiModelProperty(value = "开课学期")
    private Integer semester;

    @ApiModelProperty(value = "课程简介")
    private String coursesDescription;

    @ApiModelProperty(value = "班级编号")
    private String classesId;

    @ApiModelProperty(value = "上课第几时段")
    private Integer classTime;

    @ApiModelProperty(value = "上课位置")
    private String classroom;

    @ApiModelProperty(value = "课程可容纳人数")
    private Integer capacity;

    @ApiModelProperty(value = "教学班授课老师名字")
    private String teacherName;

    @ApiModelProperty(value = "星期几上课")
    private Integer classDay;

    @ApiModelProperty(value = "剩余人数")
    private Integer number;

}
