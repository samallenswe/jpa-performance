package com.github.saipradyu.performance;

import static com.github.saipradyu.performance.utils.Utils.POOL_SIZE;
import static com.github.saipradyu.performance.utils.Utils.TEST_SIZE;

import com.github.saipradyu.performance.service.AsyncService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AsyncBatchWrite implements CommandLineRunner {

  @NonNull
  private AsyncService asyncService;

  @Override
  public void run(String... args) throws Exception {
    int workUnit = TEST_SIZE / POOL_SIZE;
    List<CompletableFuture<Void>> futureList = new ArrayList<>();
    long startTime = System.nanoTime();
    for (int i = 0; i < POOL_SIZE; i++) {
      futureList.add(asyncService.batchWrite(workUnit));
    }
    futureList.forEach(CompletableFuture::join);
    long endTime = System.nanoTime();
    log.info("Asynchronous Batch Write took: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
  }
}
