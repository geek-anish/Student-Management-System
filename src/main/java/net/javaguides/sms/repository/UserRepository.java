package net.javaguides.sms.repository;

//import net.javaguides.sms.entity.Role;
import net.javaguides.sms.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);

//    long countByRoles(Role role);

    @Query("select u from User u where :role in (u.role)")
    List<User> existsByRole(String role);


        // Define a query method to retrieve the user's password by username
        @Query("SELECT u.password FROM User u WHERE u.email = :email")
        String findPasswordByEmail(@Param("email") String email);


    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);


}


