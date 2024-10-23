package swagger.swagger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import swagger.swagger.model.Customers;
import swagger.swagger.repository.RepositoryCustomer;

import java.util.List;

@Service
@RequiredArgsConstructor //constructor genera dependencias finales no necesarios getter o setter
public class ServiceCustomerImp implements ServiceCustomer{
    private final RepositoryCustomer repositoryCustomer;
    //metodos
    @Override
    @Transactional(readOnly = true)
    public List<Customers> findAll(){
        return repositoryCustomer.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customers findById(Long id){
        return repositoryCustomer.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Customers save(Customers customers){
        return  repositoryCustomer.save(customers);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public  Customers update(Long id, Customers customers){
        Customers tmp = repositoryCustomer.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        tmp.setCustomer_name(customers.getCustomer_name());
        return repositoryCustomer.save(tmp);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Long id){
        if (!repositoryCustomer.existsById(id)){
            throw  new RuntimeException("Customer not found");
        }
        repositoryCustomer.deleteById(id);
    }



}