package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
    private Integer id;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 详细内容
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
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
        return "Notice{" +
            "id=" + id +
            ", updateTime=" + updateTime +
            ", description=" + description +
            ", createTime=" + createTime +
        "}";
    }
}
