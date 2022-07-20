package cn.scnu.com.microservicecourse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 课程大类信息表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    private String coursesId;

    /**
     * 课程名称
     */
    private String coursesName;

    /**
     * 课程性质(选修2必修1)
     */
    private String coursesType;

    /**
     * 学时
     */
    private Integer coursesHour;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 开课学期
     */
    private Integer semester;

    /**
     * 课程简介
     */
    private String coursesDescription;



    public String getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(String coursesId) {
        this.coursesId = coursesId;
    }
    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }
    public String getCoursesType() {
        return coursesType;
    }

    public void setCoursesType(String coursesType) {
        this.coursesType = coursesType;
    }
    public Integer getCoursesHour() {
        return coursesHour;
    }

    public void setCoursesHour(Integer coursesHour) {
        this.coursesHour = coursesHour;
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
    public String getCoursesDescription() {
        return coursesDescription;
    }

    public void setCoursesDescription(String coursesDescription) {
        this.coursesDescription = coursesDescription;
    }

    @Override
    public String toString() {
        return "Courses{" +
            "coursesId=" + coursesId +
            ", coursesName=" + coursesName +
            ", coursesType=" + coursesType +
            ", coursesHour=" + coursesHour +
            ", credit=" + credit +
            ", semester=" + semester +
            ", coursesDescription=" + coursesDescription +
        "}";
    }
}
