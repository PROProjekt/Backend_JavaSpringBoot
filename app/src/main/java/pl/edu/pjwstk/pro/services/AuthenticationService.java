package pl.edu.pjwstk.pro;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthenticationService {

    UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String email, String password){

        boolean login = userService.userExist(email) && userService.isPasswordCorrect(email, password);

        if (login){
            var user = userService.findByEmail(email);
            User user1 = new User(user.getEmail(),user.getPassword());
            user1.setAuthorities(Set.of(user.getAuthority().getRole()));
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user1)); //tworze token autorycyjny
        }
        return login;

    }

}
