package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webdev.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM user u WHERE u.username=:username")
    Iterable<User> findUserByUsername(@Param("username") String u);

    @Query("SELECT u FROM user u WHERE u.username=:username AND u.password=:password")
    Iterable<User> findUserByUsernameAndPassword(@Param("username") String username,
                                                 @Param("password") String password);
}
