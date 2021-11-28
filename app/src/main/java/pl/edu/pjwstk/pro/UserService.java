package pl.edu.pjwstk.pro;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.pro.entities.RoleEntity;
import pl.edu.pjwstk.pro.entities.UserEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager em) {
        this.em = em;
    }

    public void saveUser(String userName, String password, RoleEntity roleEntity) {
        var userEntity = new UserEntity();
        userEntity.setUsername(userName);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setAuthority(roleEntity);

        em.persist(userEntity);
    }

    public UserEntity findByUserName(String username) {
        return em.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public boolean userExist(String username) {
        var isExist = em.createQuery("select ue from UserEntity ue where ue.username = :username ", UserEntity.class)
                .setParameter("username", username).getResultList();
        if (isExist.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isPasswordCorrect(String username, String password) {
        UserEntity user = findByUserName(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean isAnyUserExist() {
        return em.createQuery("select count(ue) from UserEntity ue", Long.class)
                .getSingleResult()
                > 0;
    }

    public List<UserEntity> getUsers() {
        return em.createQuery("select username from UserEntity", UserEntity.class)
                .getResultList();
    }
}