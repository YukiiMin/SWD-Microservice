package org.minhht.loginfunction.repository;


import org.minhht.loginfunction.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, String> {
}
