package swagger.swagger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swagger.swagger.model.Customers;
import swagger.swagger.repository.RepositoryCustomer;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ServiceCustomerImp implements ServiceCustomer {
    final RepositoryCustomer repositoryCustomer;//repositorio para acceder slos datos de los clientes


    public List<Customers> findAll(){return  repositoryCustomer.findAll();}


    public Customers findById(Long id){return repositoryCustomer.findById(id).orElse(null);}


    public Customers save(Customers customers){return  repositoryCustomer.save(customers);}



    public Customers update(Long id, Customers customers){
        Customers clientestmp = repositoryCustomer.findById(id)//variable temporal buscar cliente por id
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
        //actualizar datos del cliente
        clientestmp.setCustomer_name(customers.getCustomer_name());

        return repositoryCustomer.save(clientestmp);//guarda y devuelve el cliente actualizado

    }

    public void delete(Long id) {
        if (!repositoryCustomer.existsById(id)) {
            throw new RuntimeException("Cliente not found");
        }
        repositoryCustomer.deleteById(id);
    }

}
