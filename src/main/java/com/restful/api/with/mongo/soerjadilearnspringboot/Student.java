package com.restful.api.with.mongo.soerjadilearnspringboot;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Student {
  @Id
  private String id;
  private String firstName;
  private String lastname;
  @Indexed(unique = true)
  private String email;
  private Gender gender;
  private Address address;
  private List<String> favoriteSubject;
  private BigDecimal totalSpentInBook;
  private LocalDateTime createdAt;

  public Student(String firstName, String lastname, String email, Gender gender, Address address, List<String> favoriteSubject, BigDecimal totalSpentInBook, LocalDateTime createdAt) {
    this.firstName = firstName;
    this.lastname = lastname;
    this.email = email;
    this.gender = gender;
    this.address = address;
    this.favoriteSubject = favoriteSubject;
    this.totalSpentInBook = totalSpentInBook;
    this.createdAt = createdAt;
  }
}
