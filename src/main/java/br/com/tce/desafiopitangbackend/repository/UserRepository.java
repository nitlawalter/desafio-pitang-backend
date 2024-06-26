package br.com.tce.desafiopitangbackend.repository;

import br.com.tce.desafiopitangbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findByEmail(String email);
}
