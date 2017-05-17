package com.gert.security.restApi;

import com.gert.model.employer.Employer;
import com.gert.service.employer.EmployerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gert on 17.05.17.
 */
@Service("employerServiceRestApi")
public class EmployerDetailsServiceImpl implements UserDetailsService, InitializingBean {

    @Autowired
    private EmployerService employerService;

    public void afterPropertiesSet() throws Exception {
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        try {
            Employer account = employerService.findBySSO(username);
            if (account == null) {
                throw new UsernameNotFoundException("Could not find email: " + username + "in the DB.");
            }

            List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));

            ApplicationUser user = null;
            try {
                user = new ApplicationUser(account.getId().longValue(), username, account.getPassword(), true, true, true, true, auths);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(username + "not found", e);
        }
    }

}
