package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepo'daki hazır metodları kullanabilmek için extend ediyoruz.
@Repository //Optional,  to make it more clean and readable
public interface StudentRepository extends JpaRepository<Student, Long> { //Name of entity class, PK Data Type
    boolean existsByEmail(String email);




}
