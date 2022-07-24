package cn.scnu.com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Students数据传输对象", description="学生表和用户表")
public class stuVo {

    @ApiModelProperty(value = "学号")
    private Integer studentId;

    @ApiModelProperty(value = "学生名字")
    private String name;

    @ApiModelProperty(value = "学生描述")
    private String description;

    @ApiModelProperty(value = "学生专业")
    private String major;

    @ApiModelProperty(value = "学生所在年级")
    private Integer gradeId;

    @ApiModelProperty(value = "目前处于第几学期")
    private Integer semester;

    @ApiModelProperty(value = "学生入学时间")
    private Integer timeEnrollment;
}