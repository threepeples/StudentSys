package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;

/**
 * <p>
 * 年级表（专业下属年级）
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年级id(入学学年+专业号码)
     */
    private Integer gradeId;

    /**
     * 年级人数
     */
    private Integer number;

    /**
     * 专业id
     */
    private Integer majorsId;

    /**
     * 目前处于第几学期
     */
    private Integer semester;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(Integer majorsId) {
        this.majorsId = majorsId;
    }
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Grade{" +
            "gradeId=" + gradeId +
            ", number=" + number +
            ", majorsId=" + majorsId +
            ", semester=" + semester +
        "}";
    }
}
