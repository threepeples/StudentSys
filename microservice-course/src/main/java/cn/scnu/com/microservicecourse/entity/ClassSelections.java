package cn.scnu.com.microservicecourse.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 学生已选课表(也作为平时课表和成绩表)
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
@TableName("class_selections")
public class ClassSelections implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生所选教学课id
     */
    private String classesId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 所得成绩
     */
    private Double score;

    /**
     * 所得学分
     */
    private Double credit;

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId;
    }
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "ClassSelections{" +
            "classesId=" + classesId +
            ", studentId=" + studentId +
            ", score=" + score +
            ", credit=" + credit +
        "}";
    }
}
