package cn.csnu.com.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学生已选课表(也作为平时课表和成绩表)
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("class_selections")
@ApiModel(value="ClassSelections对象", description="学生已选课表(也作为平时课表和成绩表)")
public class ClassSelections implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生所选教学课id")
    private String classes_id;

    @ApiModelProperty(value = "学生id")
    private Integer student_id;

    @ApiModelProperty(value = "所得成绩")
    private Double score;

    @ApiModelProperty(value = "所得学分")
    private Double credit;


}
