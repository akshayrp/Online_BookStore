package com.thoughtworks.onlinebookstore.repository;

import com.thoughtworks.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookStoreRepository extends JpaRepositoryImplementation<Book, Long> {
    Optional<Book> findByAuthorNameAndBookEditionAndBookName(String bookName, String authorName, String bookEdition);
}
