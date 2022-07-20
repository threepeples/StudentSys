package cn.scnu.com.microservicecourse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 教师表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师编号
     */
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 教师名字
     */
    private String name;

    /**
     * 教师简介
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Teachers{" +
            "teacherId=" + teacherId +
            ", name=" + name +
            ", description=" + description +
            ", createTime=" + createTime +
        "}";
    }
}
