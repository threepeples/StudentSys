package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;

/**
 * <p>
 * 专业信息表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Majors implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    private String majorsId;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 专业所属学院
     */
    private String departmentId;

    /**
     * 专业简介
     */
    private String description;

    public String getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(String majorsId) {
        this.majorsId = majorsId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Majors{" +
            "majorsId=" + majorsId +
            ", name=" + name +
            ", departmentId=" + departmentId +
            ", description=" + description +
        "}";
    }
}
