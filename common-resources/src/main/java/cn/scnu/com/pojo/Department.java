package cn.scnu.com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学院表
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Department对象", description="学院表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学院id")
    private String department_id;

    @ApiModelProperty(value = "学院名称")
    private String name;


}
