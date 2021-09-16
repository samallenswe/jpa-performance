package com.github.saipradyu.performance.runners;

import static com.github.saipradyu.performance.utils.Utils.TEST_SIZE;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.utils.Utils;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Order(1)
@Component
@Transactional
public class SyncWrite implements CommandLineRunner {

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  //@Autowired
  //private AsyncH2Write asyncH2;

  @Override
  public void run(String... args) throws Exception {
    AsyncH2Write asyncH2 = new AsyncH2Write();
    asyncH2.setEntity(entityManager);

    long writeStart = System.nanoTime();
    asyncH2.writeToH2DB();

    long writeEnd = System.nanoTime();
    log.info("Writing to DB took: " + TimeUnit.NANOSECONDS.toMillis(writeEnd - writeStart));
  }
}
@Service
@Transactional(propagation =Propagation.NESTED)
class AsyncH2Write {
  EntityManager entityManager;

  void setEntity(EntityManager em) {
    this.entityManager = em;
  }

  @Async("taskExecutor")
  public void writeToH2DB() {
    for (int i = 0; i < TEST_SIZE; i++) {
      Person person = Utils.createRandomPerson(i + 1);
      entityManager.persist(person);
    }
  }
}
