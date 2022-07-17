package cn.csnu.com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 专业信息表
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Majors对象", description="专业信息表")
public class Majors implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业id")
    private Integer majors_id;

    @ApiModelProperty(value = "专业名称")
    private String name;

    @ApiModelProperty(value = "专业所属学院")
    private String department_id;

    @ApiModelProperty(value = "专业简介")
    private String description;


}
