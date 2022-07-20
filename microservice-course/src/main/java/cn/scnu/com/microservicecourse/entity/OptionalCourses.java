package cn.scnu.com.microservicecourse.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 可选课程信息表（由专业和课程决定）
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
@TableName("optional_courses")
public class OptionalCourses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    private String majorsId;

    /**
     * 专业可选课程id
     */
    private String coursesId;

    /**
     * 课程第几学期可选
     */
    private Integer semester;

    public String getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(String majorsId) {
        this.majorsId = majorsId;
    }
    public String getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(String coursesId) {
        this.coursesId = coursesId;
    }
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "OptionalCourses{" +
            "majorsId=" + majorsId +
            ", coursesId=" + coursesId +
            ", semester=" + semester +
        "}";
    }
}
