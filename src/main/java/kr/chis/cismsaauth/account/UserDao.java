package kr.chis.cismsaauth.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author InSeok
 * Date : 2020-08-13
 * Remark :
 */
@Repository("UserDao")
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
