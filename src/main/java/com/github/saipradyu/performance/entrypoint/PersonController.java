package com.github.saipradyu.performance.entrypoint;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PersonController {

  @NonNull
  private PersonService personService;

  @RequestMapping("/persons/{lastName}")
  public Person getPerson(@PathVariable String lastName) {
    return personService.getPerson(lastName);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/persons")
  public void addPerson(@RequestBody Person person) {
    personService.addPerson(person);
  }
}
