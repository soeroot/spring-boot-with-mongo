package com.restful.api.with.mongo.soerjadilearnspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SoerjadiLearnSpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SoerjadiLearnSpringbootApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
    return args -> {
      Address address = new Address(
          "Indonesia",
          "Surabaya",
          "IND002"
      );

      String email = "soerjadi@mail.com";

      List<String> favoriteSubjects = new ArrayList<>();
      favoriteSubjects.add("Art");
      favoriteSubjects.add("Math");
      Student student2 = new Student(
          "Soerjadi",
          "Misla",
          email,
          Gender.MALE,
          address,
          favoriteSubjects,
          BigDecimal.TEN,
          LocalDateTime.now()
      );

      // usingMongoTemplateAndQuery(repository, mongoTemplate, email, student2);

//		repository.findStudentByEmail(email)
//				.ifPresent(student -> {
//					System.out.println("student is exist");
//				});
		if (repository.findStudentByEmail(email).isPresent()){
			System.out.println("student is exist");
		}else{
			System.out.println("Inserting student " + student2);
			repository.insert(student2);
		}

    };
  }

  private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student2) {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(email));

    List<Student> students = mongoTemplate.find(query, Student.class);

    if (students.size() > 1) {
      throw new IllegalStateException("found many students with email " + email);
    }

    if (students.isEmpty()) {
      System.out.println("Inserting student " + student2);
      repository.insert(student2);
    } else {
      System.out.println("student is exist");
    }
  }
}
