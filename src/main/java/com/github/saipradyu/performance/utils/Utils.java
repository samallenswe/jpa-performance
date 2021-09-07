package com.github.saipradyu.performance.utils;

import com.github.saipradyu.performance.domain.Person;
import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
  public static final int BATCH_SIZE = 5;
  public static final int TEST_SIZE = 500;
  public static final int POOL_SIZE = 5;


  public static Person createRandomPerson() {
    Person person = new Person();
    String firstName = RandomStringUtils.randomAlphabetic(6);
    String lastName = RandomStringUtils.randomAlphabetic(6);
    person.setFirstName(firstName);
    person.setLastName(lastName);
    person.setEmail(firstName + "." + lastName + "@xyz.com");
    return person;
  }
}
