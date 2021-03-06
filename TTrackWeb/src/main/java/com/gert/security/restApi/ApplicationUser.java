package com.gert.security.restApi;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by gert on 17.05.17.
 */
public class ApplicationUser extends User {
    public static final long serialVersionUID = 42L;

    private final Long accountId;

    public ApplicationUser(Long accountId, String email, String password, boolean arg2, boolean arg3, boolean arg4, boolean arg5, Collection<? extends GrantedAuthority> authorities)
            throws IllegalArgumentException {
        super(email, password, arg2, arg3, arg4, arg5, authorities);
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return this.accountId;
    }
}
