package com.sfsb.studentsSFSB.students.repository;

import com.sfsb.studentsSFSB.students.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer> {
}
