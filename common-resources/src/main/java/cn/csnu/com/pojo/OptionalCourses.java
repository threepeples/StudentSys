package cn.csnu.com.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 可选课程信息表（由专业和课程决定）
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("optional_courses")
@ApiModel(value="OptionalCourses对象", description="可选课程信息表（由专业和课程决定）")
public class OptionalCourses implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业id")
    private String majors_id;

    @ApiModelProperty(value = "专业可选课程id")
    private String courses_id;

    @ApiModelProperty(value = "课程第几学期可选")
    private Integer semester;


}
