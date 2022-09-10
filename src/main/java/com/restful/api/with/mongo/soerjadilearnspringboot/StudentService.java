package com.restful.api.with.mongo.soerjadilearnspringboot;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> fetchAllStudents(){
    return studentRepository.findAll();
  }
}
