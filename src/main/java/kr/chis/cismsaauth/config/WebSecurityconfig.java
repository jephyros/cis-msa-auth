package kr.chis.cismsaauth.config;

import kr.chis.cismsaauth.account.UserInformationServerce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author InSeok
 * Date : 2020-08-13
 * Remark :
 */
@EnableWebSecurity
public class WebSecurityconfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserInformationServerce userInformationServerce;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2 데이터를 확인하기위해 h2-console url의 권한을 permitAll으로 바꾸어 줍니다.
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .and().csrf().disable()
                .headers().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInformationServerce);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }
}
