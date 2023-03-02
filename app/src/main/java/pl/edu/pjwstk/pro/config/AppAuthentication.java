package pl.edu.pjwstk.pro;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
public class AppAuthentication extends AbstractAuthenticationToken {
    private final User authenticationUser;

    public AppAuthentication(User authenticationUser) {
        super(toGrantedAuthorities(authenticationUser.getAuthorities()));
        this.authenticationUser = authenticationUser;
        setAuthenticated(true);
    }

    private static Collection<? extends GrantedAuthority> toGrantedAuthorities(Set<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Object getCredentials() {
        return authenticationUser.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return authenticationUser;
    }
}