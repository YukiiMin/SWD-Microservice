package org.minhht.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducerService {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendProduct(String name, double price) {
        String message = String.format("{\"name\": \"%s\", \"price\": %f}", name, price);
        kafkaTemplate.send("product-topic", message);
    }
}
