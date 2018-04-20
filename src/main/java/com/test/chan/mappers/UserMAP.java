package com.test.chan.mappers;

import com.test.chan.beans.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserMAP {

    public LoginUser findByUsername(String emailAddress);

    public UserDetails findById(String userId);
}
