package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;

/**
 * <p>
 * 教学班信息表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级编号
     */
    private String classesId;

    /**
     * 上课第几时段
     */
    private Integer classTime;

    /**
     * 上课位置
     */
    private String classroom;

    /**
     * 课程可容纳人数
     */
    private Integer capacity;

    /**
     * 教学班所属课程编号
     */
    private String coursesId;

    /**
     * 教学班授课老师名字
     */
    private String teacherName;

    /**
     * 星期几上课
     */
    private Integer classDay;

    /**
     * 剩余人数
     */
    private Integer number;


    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId;
    }
    public Integer getClassTime() {
        return classTime;
    }

    public void setClassTime(Integer classTime) {
        this.classTime = classTime;
    }
    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public String getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(String coursesId) {
        this.coursesId = coursesId;
    }
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public Integer getClassDay() {
        return classDay;
    }

    public void setClassDay(Integer classDay) {
        this.classDay = classDay;
    }

    public void setNumber(String classesId) {
        this.number = number;
    }
    public Integer getNumber() {
        return number;
    }



    @Override
    public String toString() {
        return "Classes{" +
            "classesId=" + classesId +
            ", classTime=" + classTime +
            ", classroom=" + classroom +
            ", capacity=" + capacity +
            ", coursesId=" + coursesId +
            ", teacherName=" + teacherName +
            ", classDay=" + classDay +
            ", number=" + number +
        "}";
    }
}
