package cn.csnu.com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 教学班信息表
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Classes对象", description="教学班信息表")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级编号")
    private String classes_id;

    @ApiModelProperty(value = "上课第几时段")
    private Integer class_time;

    @ApiModelProperty(value = "上课位置")
    private String classroom;

    @ApiModelProperty(value = "课程可容纳人数")
    private Integer capacity;

    @ApiModelProperty(value = "教学班所属课程编号")
    private Integer courses_id;

    @ApiModelProperty(value = "教学班授课老师名字")
    private String teacher_name;

    @ApiModelProperty(value = "星期几上课")
    private Integer class_day;

    @ApiModelProperty(value = "剩余人数")
    private Integer number;


}
