package com.thoughtworks.onlinebookstore.repository;

import com.thoughtworks.onlinebookstore.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
     OrderDetails findTopByOrderByOrderIdDesc();
     OrderDetails findTopByConsumerEmailOrderByOrderIdDesc(String emailID);
}
