package cn.scnu.com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("修改密码接收类")
@Data
public class changePwdVo {

    @ApiModelProperty("旧密码")
    private String oldPwd;

    @ApiModelProperty("新密码")
    private String newPwd;
}
