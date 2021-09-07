package com.github.saipradyu.performance.service;

import com.github.saipradyu.performance.domain.Person;
import com.github.saipradyu.performance.utils.Utils;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersistenceService {

  @Transactional
  public void writeBatchToDB(EntityManager entityManager, int workUnit) {
    for (int i = 0; i < workUnit; i++) {
      Person person = Utils.createRandomPerson();
      entityManager.persist(person);
    }
  }
}
