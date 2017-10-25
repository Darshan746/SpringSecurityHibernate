package com.darshan.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darshan.bean.User;
import com.darshan.bean.UserRole;
import com.darshan.dao.UserDao;

@Service("userDetails")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	
	@Override
	public UserDetails loadUserByUsername(final String username)
		throws UsernameNotFoundException {

		User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities =
                                      buildUserAuthority(user.getUserRole());

		return (UserDetails) buildUserForAuthentication(user, authorities);

	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
		List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
			true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) 
	{
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>();
		for (UserRole userRole : userRoles) {
			Result.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		

		return Result;
	}


}
