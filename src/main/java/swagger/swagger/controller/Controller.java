package swagger.swagger.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import swagger.swagger.model.Customers;
import swagger.swagger.service.ServiceCustomer;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Service
public class Controller {
    private final ServiceCustomer serviceCustomer;
    //metodos para solicitudes http
    @GetMapping
    public List<Customers> findAll(){
        return serviceCustomer.findAll();
    }

    @GetMapping("/{id}")
    public Customers findById(@PathVariable Long id){
        return serviceCustomer.findById(id);
    }

    @PostMapping()
    public  Customers save(@RequestBody Customers customers){
        return serviceCustomer.save(customers);
    }

    @PutMapping("/{id}")
    public  Customers update(@PathVariable Long id, @RequestBody Customers customers){
        return serviceCustomer.update(id, customers);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        serviceCustomer.delete(id);
    }
}