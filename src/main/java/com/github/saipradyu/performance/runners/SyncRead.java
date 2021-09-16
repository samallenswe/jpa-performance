package com.github.saipradyu.performance.runners;

import com.github.saipradyu.performance.domain.Person;
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
@Order(2)
@Component
@Transactional
public class SyncRead implements CommandLineRunner {

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  @Override
  public void run(String... args) throws Exception {
    long readStart = System.nanoTime();
    readFromH2DB();
    long readEnd = System.nanoTime();
    log.info("Reading from DB took: " + TimeUnit.NANOSECONDS.toMillis(readEnd - readStart));
  }

  @Transactional
  public void readFromH2DB() {
    for (int i = 1; i <= 50; i++) {
      Person person = entityManager.find(Person.class, (long) i);
      log.info("Found person " + i + " with " + person.toString());
    }
  }
}
