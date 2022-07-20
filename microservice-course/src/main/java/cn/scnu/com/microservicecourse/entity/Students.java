package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 学生名字
     */
    private String name;

    /**
     * 学生登录密码
     */
    private String pwd;

    /**
     * 学生描述
     */
    private String description;

    /**
     * 学生专业id
     */
    private Integer majorsId;

    /**
     * 学生所在年级id
     */
    private Integer gradeId;

    /**
     * 目前处于第几学期
     */
    private Integer semester;

    /**
     * 学生入学时间
     */
    private Integer timeEnrollment;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(Integer majorsId) {
        this.majorsId = majorsId;
    }
    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public Integer getTimeEnrollment() {
        return timeEnrollment;
    }

    public void setTimeEnrollment(Integer timeEnrollment) {
        this.timeEnrollment = timeEnrollment;
    }

    @Override
    public String toString() {
        return "Students{" +
            "studentId=" + studentId +
            ", name=" + name +
            ", pwd=" + pwd +
            ", description=" + description +
            ", majorsId=" + majorsId +
            ", gradeId=" + gradeId +
            ", semester=" + semester +
            ", timeEnrollment=" + timeEnrollment +
        "}";
    }
}
