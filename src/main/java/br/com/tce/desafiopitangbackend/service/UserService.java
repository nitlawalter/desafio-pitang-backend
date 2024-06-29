package br.com.tce.desafiopitangbackend.service;

import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login: " + username);
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean findByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean findByLogin(String login) {
        return userRepository.findByLogin(login) != null;
    }
}
