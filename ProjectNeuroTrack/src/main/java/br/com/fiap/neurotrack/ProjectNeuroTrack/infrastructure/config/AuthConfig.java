package br.com.fiap.neurotrack.ProjectNeuroTrack.infrastructure.config;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.UserSysRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {

    private final UserSysRepository  userRepository;

    public AuthConfig(UserSysRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
