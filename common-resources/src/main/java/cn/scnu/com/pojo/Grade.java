package cn.scnu.com.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 年级表（专业下属年级）
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Grade对象", description="年级表（专业下属年级）")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年级id(入学学年+专业号码)")
    private Integer grade_id;

    @ApiModelProperty(value = "年级人数")
    private Integer number;

    @ApiModelProperty(value = "专业id")
    private Integer majors_id;

    @ApiModelProperty(value = "目前处于第几学期")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Integer semester;


}
