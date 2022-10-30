package com.ekar.concurrencyapp.data.repositories;

import com.ekar.concurrencyapp.data.entities.RequestResponseLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseLogRepository extends CrudRepository<RequestResponseLog, Long> {
}
