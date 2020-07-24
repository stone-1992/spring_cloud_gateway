package com.stone.auth.model;

import com.stone.auth.po.UserAuth;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

/**
 * @classname LoginUser
 * @description 用户授权实体绑定spring security
 * @date 2020/3/30 16:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LoginUser extends UserAuth implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getIdentifier();
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
        return getStatus() == 1? true:false;
    }
}
