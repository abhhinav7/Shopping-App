package com.HyperSrot.assignment.shopping_app.facade;

import com.HyperSrot.assignment.shopping_app.entity.Product;
import com.HyperSrot.assignment.shopping_app.responsedto.InventoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductFacade {
    List<InventoryDTO> findAll();

    ResponseEntity<String> addProduct(Product product);

    ResponseEntity<String> deleteById(Long id);

    Product findById(Long productId);

    void save(Product product);
}
