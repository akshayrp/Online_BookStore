package com.thoughtworks.onlinebookstore.service;

import com.thoughtworks.onlinebookstore.model.Consumer;
import com.thoughtworks.onlinebookstore.repository.IConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServices implements IConsumerServices {

    @Autowired
    private IConsumerRepository consumerRepository;

    public Consumer setDetails(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

}
