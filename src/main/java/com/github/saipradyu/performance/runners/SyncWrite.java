package com.github.saipradyu.performance.runners;

import com.github.saipradyu.performance.service.PersistenceService;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(1)
@RequiredArgsConstructor
@Component
public class SyncWrite implements CommandLineRunner {

  @NonNull
  private PersistenceService persistenceService;


  @Override
  public void run(String... args) throws Exception {
    long writeStart = System.nanoTime();
    persistenceService.writeToH2DB();
    long writeEnd = System.nanoTime();
    log.info("Writing to DB took: " + TimeUnit.NANOSECONDS.toMillis(writeEnd - writeStart));
  }
}
