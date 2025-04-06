package com.tool.security;

import com.tool.entity.Role;
import com.tool.entity.User;
import com.tool.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationProviderCustom implements AuthenticationProvider {
    UserService userService;
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username == null || password == null)
            throw new RuntimeException("Username and password is require");
        User user = userService.getUser(username);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Bad credential");

        return usernamePasswordAuthenticationToken(user);
    }

    private static UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (!roles.isEmpty()) {
            roles.stream().map(
                    item -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + item.getName()))
            );
        }
        UserDetails principal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
