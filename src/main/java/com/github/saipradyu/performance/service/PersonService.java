package com.github.saipradyu.performance.service;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.domain.rpsy.PersonRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  @NonNull
  private PersonRepository personRepository;

  public Person getPerson(String lastName) {
    return personRepository.findFirstByLastName(lastName);
  }

  public void addPerson(Person person) {
    personRepository.save(person);
  }

}
