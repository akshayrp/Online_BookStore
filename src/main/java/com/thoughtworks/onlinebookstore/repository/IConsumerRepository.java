package com.thoughtworks.onlinebookstore.repository;

import com.thoughtworks.onlinebookstore.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsumerRepository extends JpaRepository<Consumer, Long> {
    Consumer findConsumerByName(String name);
}
