package kr.chis.cismsaauth.account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author InSeok
 * Date : 2020-08-13
 * Remark :
 */
@Service("UserInformationServerce")
public class UserInformationServerce implements UserDetailsService {

    @Resource(name="UserDao")
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        // 저장된 ID가 없을때 throw 시켜줍니다.
        if(user == null) {
            throw new UsernameNotFoundException("wrongId"); // 저장된 ID 없음
        }
        return makeLoginUser(user);

    }

    // UserInformation 값 주입 해 줍니다.
    public UserInformation makeLoginUser(User user) {

        UserInformation loginUser  = new UserInformation();


        List<GrantedAuthority> authoritylist = new ArrayList<>();
        switch(user.getUserType()) {
            case 0 :
                // admin
                authoritylist.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case 1 :
                // user
                authoritylist.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
        }

        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setAuthorities(authoritylist);

        return loginUser;
    }
}
