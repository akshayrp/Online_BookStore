package com.thoughtworks.onlinebookstore.repository;

import com.thoughtworks.onlinebookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookShopRepository extends JpaRepository<Books, Integer> {
}
