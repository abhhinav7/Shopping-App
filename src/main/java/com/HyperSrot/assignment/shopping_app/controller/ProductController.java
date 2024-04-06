package com.HyperSrot.assignment.shopping_app.controller;

import com.HyperSrot.assignment.shopping_app.entity.Product;
import com.HyperSrot.assignment.shopping_app.facade.ProductFacade;
import com.HyperSrot.assignment.shopping_app.responsedto.InventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductFacade productFacade;

    @GetMapping("/inventory")
    public List<InventoryDTO> sendProducts(){
        return productFacade.findAll();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        if(product==null) {
            return ResponseEntity.badRequest().body("Product cannot be null");
        }

        try {
            return productFacade.addProduct(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try {
            return productFacade.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product: " + e.getMessage());
        }
    }

}
