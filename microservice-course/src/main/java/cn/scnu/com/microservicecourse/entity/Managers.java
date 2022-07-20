package cn.scnu.com.microservicecourse.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author tt
 * @since 2022-06-25
 */
public class Managers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    private Integer id;

    /**
     * 管理员名字
     */
    private String name;

    /**
     * 管理员账号
     */
    private String account;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Managers{" +
            "id=" + id +
            ", name=" + name +
            ", account=" + account +
            ", pwd=" + pwd +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
