package com.github.saipradyu.performance.service;

import java.util.concurrent.CompletableFuture;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AsyncService {

  @NonNull
  private PersistenceService service;
  @PersistenceUnit
  private EntityManagerFactory emf;

  @Async
  public CompletableFuture<Void> batchWrite(int workUnit) {
    EntityManager entityManager = emf.createEntityManager();
    service.writeBatchToDB(entityManager, workUnit);
    return CompletableFuture.completedFuture(null);
  }
}
