package com.example.demo.message.request;

import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Zhe Li
 * @date 2020/05/01
 */
@ApiModel(value = "用户登录的表格",description = "用户输入用户名和密码登录")
public class LoginForm {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank
    @Size(min=3, max = 60)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
