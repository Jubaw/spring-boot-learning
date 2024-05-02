package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepo'daki hazır metodları kullanabilmek için extend ediyoruz.
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {







}
