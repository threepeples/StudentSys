package cn.csnu.com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 课程大类信息表
 * </p>
 *
 * @author su_jue
 * @since 2022-07-17
 */

/**
 * CLS:添加get set  toString方法
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Courses对象", description="课程大类信息表")
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程编号")
    @TableId(value = "courses_id", type = IdType.AUTO)
    private Integer courses_id;

    @ApiModelProperty(value = "课程名称")
    private String courses_name;

    @ApiModelProperty(value = "课程性质(选修2必修1)")
    private String courses_type;

    @ApiModelProperty(value = "学时")
    private Integer courses_hour;

    @ApiModelProperty(value = "学分")
    private Integer credit;

    @ApiModelProperty(value = "开课学期")
    private Integer semester;

    @ApiModelProperty(value = "课程简介")
    private String courses_description;

    public Integer getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(Integer courses_id) {
        this.courses_id = courses_id;
    }

    public String getCourses_name() {
        return courses_name;
    }

    public void setCourses_name(String courses_name) {
        this.courses_name = courses_name;
    }

    public String getCourses_type() {
        return courses_type;
    }

    public void setCourses_type(String courses_type) {
        this.courses_type = courses_type;
    }

    public Integer getCourses_hour() {
        return courses_hour;
    }

    public void setCourses_hour(Integer courses_hour) {
        this.courses_hour = courses_hour;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getCourses_description() {
        return courses_description;
    }

    public void setCourses_description(String courses_description) {
        this.courses_description = courses_description;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Courses{" +
                "courses_id=" + courses_id +
                ", courses_name='" + courses_name + '\'' +
                ", courses_type='" + courses_type + '\'' +
                ", courses_hour=" + courses_hour +
                ", credit=" + credit +
                ", semester=" + semester +
                ", courses_description='" + courses_description + '\'' +
                '}';
    }
}
