package com.thoughtworks.onlinebookstore.repository;

import com.thoughtworks.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookStoreRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByAuthorNameAndBookName(String bookName, String authorName);
}
