package km.exam9.forum.repository;

import km.exam9.forum.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByLoginAndEmail(String login, String email);
}
