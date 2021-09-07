package com.github.saipradyu.performance;

import static com.github.saipradyu.performance.utils.Utils.TEST_SIZE;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.domain.rpsy.PersonRepository;
import com.github.saipradyu.performance.utils.Utils;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(2)
@RequiredArgsConstructor
@Component
public class LevelOneCacheRead implements CommandLineRunner {

  @NonNull
  private PersonRepository personRepository;

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  @Override
  public void run(String... args) throws Exception {
    personRepository.deleteAll();
    saveToPersistenceContext();
    long readStart = System.nanoTime();
    readFromLevelOneCache();
    long readEnd = System.nanoTime();
    log.info("Reading from L1 cache took: " + TimeUnit.NANOSECONDS.toMillis(readEnd - readStart));

  }

  public void saveToPersistenceContext() {
    for (int i = 0; i < TEST_SIZE; i++) {
      Person person = Utils.createRandomPerson(i+1);
      entityManager.persist(person);
    }
  }

  public void readFromLevelOneCache() {
    for (int i = 1; i <= 50; i++) {
      Person person = entityManager.find(Person.class, (long) i);
      log.info("Found person " + i + " with " + person.toString());
    }
  }

}
