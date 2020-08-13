package kr.chis.cismsaauth.account;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author InSeok
 * Date : 2020-08-13
 * Remark :
 */
@Data
public class UserInformation implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
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
        return true;
    }
}
