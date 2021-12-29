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

    public void saveUser(String email, String firstname, String lastname, String password,String birth_date, RoleEntity roleEntity) {
        var userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setFirstname(firstname);
        userEntity.setLastname(lastname);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setBirth_date(birth_date);
        userEntity.setAuthority(roleEntity);

        em.persist(userEntity);
    }

    public UserEntity findByEmail(String email) {
        return em.createQuery("select ue from UserEntity ue where ue.email = :email", UserEntity.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public boolean userExist(String email) {
        var isExist =em.createQuery("select ue from UserEntity ue where ue.email = :email", UserEntity.class)
                .setParameter("email", email).getResultList();
        if (isExist.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isPasswordCorrect(String email, String password) {
        UserEntity user = findByEmail(email);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean isAnyUserExist() {
        return em.createQuery("select count(ue) from UserEntity ue", Long.class)
                .getSingleResult()
                > 0;
    }

    public List<UserEntity> getUsers() {
        return em.createQuery("select email from UserEntity", UserEntity.class)
                .getResultList();
    }
}