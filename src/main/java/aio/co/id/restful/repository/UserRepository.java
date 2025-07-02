package aio.co.id.restful.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aio.co.id.restful.entity.User;

public interface  UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);
}
