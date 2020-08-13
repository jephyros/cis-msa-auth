package kr.chis.cismsaauth.config;

import kr.chis.cismsaauth.account.User;
import kr.chis.cismsaauth.account.UserDao;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author InSeok
 * Date : 2020-08-13
 * Remark :
 */
@Component
public class DataInitializer implements ApplicationRunner {

    @Resource(name="UserDao")
    private UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User newUser = new User();
        PasswordEncoder passwordEncoder;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        newUser.setUsername("cis");
        newUser.setPassword(passwordEncoder.encode("1234"));
        newUser.setUserType(0);
        newUser.setDate(new Date());
        userDao.save(newUser);


    }
}
