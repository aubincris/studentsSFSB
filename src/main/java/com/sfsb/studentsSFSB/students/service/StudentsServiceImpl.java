package com.sfsb.studentsSFSB.students.service;

import com.sfsb.studentsSFSB.students.model.Students;
import com.sfsb.studentsSFSB.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentService{

    private StudentRepository studentRepo;

    @Autowired
    public StudentsServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Students> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Students findByID(int theId) {
        Optional<Students> result = studentRepo.findById(theId);

        Students theStudents = null;
        if(result.isPresent()){
            theStudents = result.get();
        }
        else{
            throw new RuntimeException("Did not find student ID - " + theId);
        }
        return theStudents;
    }

    @Override
    public void save(Students theStudents) {
        studentRepo.save(theStudents);

    }

    @Override
    public void deleteById(int theId) {
        studentRepo.deleteById(theId);

    }
}
