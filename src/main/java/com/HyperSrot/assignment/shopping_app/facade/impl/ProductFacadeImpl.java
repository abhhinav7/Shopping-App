package com.HyperSrot.assignment.shopping_app.facade.impl;

import com.HyperSrot.assignment.shopping_app.entity.Product;
import com.HyperSrot.assignment.shopping_app.facade.ProductFacade;
import com.HyperSrot.assignment.shopping_app.responsedto.InventoryDTO;
import com.HyperSrot.assignment.shopping_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    ProductService productService;

    @Override
    public List<InventoryDTO> findAll() {

        List<Product> productList = productService.findAll();
        List<InventoryDTO> inventoryDTOList = new ArrayList<>();
        for(Product product:productList){
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setOrdered(0L);
            inventoryDTO.setPrice(product.getPrice());
            inventoryDTO.setAvailable(product.getQuantityAvailable());
            inventoryDTOList.add(inventoryDTO);
        }
        return  inventoryDTOList;
    }

    @Override
    public ResponseEntity<String> addProduct(Product product) {
        if(product.getName()==null || product.getPrice()==0.0d || product.getQuantityAvailable()==null){
            return ResponseEntity.badRequest().body("Could not add a product as it does not contains necessary information");
        }
        productService.save(product);
        return ResponseEntity.accepted().body("Successfully added: " + product.getName());
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if(productService.findById(id).isEmpty()){
          return ResponseEntity.badRequest().body("Could Not find the product with this id");
        }
        productService.deleteById(id);
        return ResponseEntity.accepted().body("Successfully Deleted the product");
    }

    @Override
    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productService.findById(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    @Override
    public void save(Product product) {
        productService.save(product);
    }
}
