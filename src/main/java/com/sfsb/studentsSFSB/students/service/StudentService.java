package com.sfsb.studentsSFSB.students.service;

import com.sfsb.studentsSFSB.students.model.Students;

import java.util.List;

public interface StudentService {

    public List<Students> findAll();

    public Students findByID(int theId);

    public void save(Students theStudents);

    public void deleteById(int theId);
}
