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

    public boolean login(String userName, String password){

        boolean login = userService.userExist(userName) && userService.isPasswordCorrect(userName, password);

        if (login){
            var user = userService.findByUserName(userName);
            User user1 = new User(user.getUsername(),user.getPassword());
            user1.setAuthorities(Set.of(user.getAuthority().getRole()));
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user1)); //tworze token autorycyjny
        }
        return login;

    }

}
