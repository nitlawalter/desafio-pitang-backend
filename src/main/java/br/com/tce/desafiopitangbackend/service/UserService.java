package br.com.tce.desafiopitangbackend.service;

import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.repository.CarRepository;
import br.com.tce.desafiopitangbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private CarRepository carRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
