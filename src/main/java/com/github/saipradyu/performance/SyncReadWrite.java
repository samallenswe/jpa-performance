package com.github.saipradyu.performance;

import static com.github.saipradyu.performance.utils.Utils.TEST_SIZE;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.utils.Utils;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Order(1)
@Component
public class SyncReadWrite implements CommandLineRunner {

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  @Override
  public void run(String... args) throws Exception {
    long writeStart = System.nanoTime();
    writeToH2DB();
    long writeEnd = System.nanoTime();
    log.info("Writing to DB took: " + TimeUnit.NANOSECONDS.toMillis(writeEnd - writeStart));
    long readStart = System.nanoTime();
    readFromH2DB();
    long readEnd = System.nanoTime();
    log.info("Reading from DB took: " + TimeUnit.NANOSECONDS.toMillis(readEnd - readStart));
  }

  @Transactional
  public void writeToH2DB() {
    for (int i = 0; i < TEST_SIZE; i++) {
      Person person = Utils.createRandomPerson(i + 1);
      entityManager.persist(person);
    }
  }

  @Transactional
  public void readFromH2DB() {
    for (int i = 1; i <= 50; i++) {
      Person person = entityManager.find(Person.class, (long) i);
      log.info("Found person " + i + " with " + person.toString());
    }
  }
}
