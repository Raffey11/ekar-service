package com.ekar.concurrencyapp.data.repositories;

import com.ekar.concurrencyapp.data.entities.ThreadTimestamp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadTimestampRepository extends CrudRepository<ThreadTimestamp, Long> {
}
