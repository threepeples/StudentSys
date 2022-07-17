package cn.csnu.com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Students对象", description="学生表")
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生id")
    private Integer student_id;

    @ApiModelProperty(value = "学生名字")
    private String name;

    @ApiModelProperty(value = "学生描述")
    private String description;

    @ApiModelProperty(value = "学生专业id")
    private Integer majors_id;

    @ApiModelProperty(value = "学生所在年级id")
    private Integer grade_id;

    @ApiModelProperty(value = "目前处于第几学期")
    private Integer semester;

    @ApiModelProperty(value = "学生入学时间")
    private Integer time_enrollment;


}
