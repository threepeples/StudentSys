package cn.scnu.com.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


/**
 * @ClassName NoticeList
 * @Description
 * @date 2022/7/26 22:50
 * @Version 1.0
 * @Author HJW
 */
@Data
public class NoticeList {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告id")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "标题")
    private String title;
}