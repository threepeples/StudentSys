package cn.scnu.com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 教师表
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Teachers对象", description="教师表")
public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师编号")
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacher_id;

    @ApiModelProperty(value = "教师名字")
    private String name;

    @ApiModelProperty(value = "教师简介")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;


}
