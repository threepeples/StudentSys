package cn.scnu.com.pojo;

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
