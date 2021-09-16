package com.github.saipradyu.performance.service;

import static com.github.saipradyu.performance.utils.Utils.TEST_SIZE;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.domain.rpsy.PersonRepository;
import com.github.saipradyu.performance.utils.Utils;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PersistenceService {

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  @NonNull
  private PersonRepository repository;

  @Transactional
  public void writeToH2DB() {
    for (int i = 0; i < TEST_SIZE; i++) {
      Person person = Utils.createRandomPerson(i + 1);
      entityManager.persist(person);
    }
    entityManager.flush();
  }

  @Transactional
  public void readFromH2DB() {
    for (int i = 1; i <= 50; i++) {
      Person person = entityManager.find(Person.class, (long) i);
      log.info("Found person " + i + " with " + person.toString());
    }
  }

  @Async
  @Transactional
  public CompletableFuture<Void> doAsyncBatch(List<Person> personList) {
    repository.saveAll(personList);
    return CompletableFuture.completedFuture(null);
  }
}
