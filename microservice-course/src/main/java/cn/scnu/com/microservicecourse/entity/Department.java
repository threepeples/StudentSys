package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private String departmentId;

    private String name;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
            "departmentId=" + departmentId +
            ", name=" + name +
        "}";
    }
}
