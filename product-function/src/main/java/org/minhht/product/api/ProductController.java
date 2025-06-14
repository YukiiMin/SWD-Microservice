package org.minhht.product.api;

import org.minhht.product.model.Product;
import org.minhht.product.repository.ProductRepository;
import org.minhht.product.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        Product saveProduct = productRepository.save(product);
        kafkaProducerService.sendProduct(product.getName(), product.getPrice());
        return saveProduct;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }
}
