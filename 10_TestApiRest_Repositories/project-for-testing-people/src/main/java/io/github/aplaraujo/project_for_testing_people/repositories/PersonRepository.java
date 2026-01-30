package io.github.aplaraujo.project_for_testing_people.repositories;

import io.github.aplaraujo.project_for_testing_people.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    @Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
    Person findByJPQL(String firstName, String lastName);

    @Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findByJPQLNamedParameters(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "SELECT * FROM tb_person p WHERE p.first_name =?1 AND p.last_name =?2", nativeQuery = true)
    Person findByNativeSQL(String firstName, String lastName);

    @Query(value = "SELECT * FROM tb_person p WHERE p.first_name =:firstName AND p.last_name =:lastName", nativeQuery = true)
    Person findByNativeSQLWithNamedParameters(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
