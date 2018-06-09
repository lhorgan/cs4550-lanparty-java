package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM user u WHERE u.username=:username")
    Iterable<User> findUserByUsername(@Param("username") String u);

   
}
