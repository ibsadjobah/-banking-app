package com.bank_app.repositories;

import com.bank_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

   /* List<User> findAllByFirstname(String firstname);

    List<User> findAllByFirstnameContaining(String firstname);

    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    List<User> findAllByAccountIban(String iban);

    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    // Requete Jpql
    @Query("from User WHERE  firstname = :fn")
    List<User> seachByFirstname(@Param("fn") String firstname);

    @Query("from User WHERE  firstname = '%:firstname%'")
    List<User> seachByFirstContaining(String firstname);

    @Query("from  User u inner join Account a on u.id = a.user.id WHERE  a.iban = :iban")
    List<User> searchIban(String iban);

    @Query(value = "select  * from  User u inner join Account a on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
    List<User> searchIbanNative(String iban);*/


}
