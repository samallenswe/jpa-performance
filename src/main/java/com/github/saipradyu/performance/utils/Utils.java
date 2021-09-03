package com.github.saipradyu.performance.utils;

import com.github.saipradyu.performance.domain.Person;

public class Utils {

  public static Person createPerson(int i) {
    Person person = new Person();
    person.setFirstName("firstName" + i);
    person.setLastName("lastName" + i);
    person.setEmail("email" + i + "@xyz.com");
    return person;
  }
}
