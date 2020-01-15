package com.thoughtworks.onlinebookstore.repository;

import com.thoughtworks.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookStoreRepository extends JpaRepository<Book, Long> {

}
