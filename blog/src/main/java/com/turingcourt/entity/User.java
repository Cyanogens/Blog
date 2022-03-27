package com.turingcourt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

/**
 * (User)实体类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = -67476734178161309L;

    private Integer id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不应为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不应为空")
    private String password;
    /**
     * 用户是否可用
     */
    private Boolean isEnable = true;
    /**
     * 性别
     */
    private String sex;

    /**
     * 密保问题
     */
    @NotBlank(message = "密保问题不应为空")
    private String problem;

    /**
     * 密保答案
     */
    @NotBlank(message = "密保答案不应为空")
    private String ans;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
