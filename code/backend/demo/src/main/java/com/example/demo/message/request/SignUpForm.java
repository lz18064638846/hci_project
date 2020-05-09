package com.example.demo.message.request;

import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * @author Zhe Li
 * @date 2020/05/01
 */
@ApiModel(value = "注册表格",description = "用于用户提交注册信息")
public class SignUpForm {
    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @ApiModelProperty(value = "邮箱",required = true,notes = "有格式验证且不能重复")
    @Email
    private String email;

    @ApiModelProperty(value = "手机号码",required = true,
            notes = "必须以13,14,15,18,17开头且为11位",dataType = "String")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}")
    private String tel;

    @ApiModelProperty(value = "真名")
    private String realName;

    @ApiModelProperty(value = "性别",dataType = "String",notes = "男/女")
    private String sex;

    public SignUpForm(@NotBlank @Size(min = 3, max = 50) String username,@NotBlank @Size(min = 6, max = 40) String password) {
        this.username = username;
        this.password = password;
    }

    public SignUpForm() {
    }

    public SignUpForm(@NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 40) String password, @Email String email, @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}")
            String tel, String realName, String sex) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.realName = realName;
        this.sex = sex;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}