package com.cmedresearch.officemaptool.service.auth;

import com.cmedresearch.officemaptool.util.SHA256PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) {
    System.out.println("User details service: " + username);
    if (!"admin".equals(username)) {
      return null;
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ADMIN"));
    String encoded = new SHA256PasswordEncoder().encode("admin123");
    return new User("admin", encoded, authorities);
  }
}
