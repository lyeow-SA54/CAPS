package sg.edu.iss.team5.services;

import javax.persistence.EntityExistsException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.edu.iss.team5.repositories.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepository;

    public UserDetailsServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityExistsException("User " + username + " doesn't exist"));
    }
}
