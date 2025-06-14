package org.minhht.product.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "product-topic", groupId = "product-group")
    public void consume(String message) {
        System.out.println("Product Service Rêcived: " + message);
    }
}
