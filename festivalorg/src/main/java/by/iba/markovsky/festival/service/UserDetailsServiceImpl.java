package by.iba.markovsky.festival.service;

import by.iba.markovsky.festival.model.WebIdentity;
import by.iba.markovsky.festival.repository.WebIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("webIdentityRepository")
    private WebIdentityRepository webIdentityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebIdentity webIdentity = webIdentityRepository.findByUsername(username);
        if (webIdentity == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(webIdentity.getRoleType().name());
        grantList.add(authority);

        UserDetails userDetails = new User(webIdentity.getUsername(), webIdentity.getPassword(), grantList);

        return userDetails;
    }

}
