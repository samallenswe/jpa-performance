package com.github.saipradyu.performance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="PERSON")
public class Person {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(name="FIRST_NAME")
  private String firstName;

  @Column(name="LAST_NAME")
  private String lastName;

  @Column(name="EMAIL")
  private String email;
}
