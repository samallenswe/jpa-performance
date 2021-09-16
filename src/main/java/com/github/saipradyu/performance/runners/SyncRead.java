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
@Order(2)
@Component
@RequiredArgsConstructor
public class SyncRead implements CommandLineRunner {

  @NonNull
  private PersistenceService persistenceService;

  @Override
  public void run(String... args) throws Exception {
    long readStart = System.nanoTime();
    persistenceService.readFromH2DB();
    long readEnd = System.nanoTime();
    log.info("Reading from DB took: " + TimeUnit.NANOSECONDS.toMillis(readEnd - readStart));
  }
}
