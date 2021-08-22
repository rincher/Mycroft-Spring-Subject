package is.recruit.mycroft.spring.subjects.config;

import is.recruit.mycroft.spring.subjects.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";

    private final User user;

    public UserDetailsImpl(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
