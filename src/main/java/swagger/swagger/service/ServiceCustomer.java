package swagger.swagger.service;


import swagger.swagger.model.Customers;

import java.util.List;

public interface ServiceCustomer {
    public List<Customers> findAll();
    public Customers findById(Long id);
    public Customers save(Customers customers);
    public Customers update(Long id, Customers customers);
    public void  delete(Long id);
}

