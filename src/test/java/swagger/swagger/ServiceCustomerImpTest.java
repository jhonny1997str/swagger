package swagger.swagger;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import swagger.swagger.model.Customers;
import swagger.swagger.repository.RepositoryCustomer;
import swagger.swagger.service.ServiceCustomerImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceCustomerImpTest {
    //creo variable que almacene customers
    private Customers customers;
    //indico el mock simulado es el repo porque realiza ooperaciones con customers
    @Mock
    private RepositoryCustomer repositoryCustomer;
    //inyecto el objeto simulado en el servicio que es el que realiza las pruebas
    @InjectMocks
    private ServiceCustomerImp serviceCustomerImp;
    //uso before antes de cada prueba y preparo el entorno
    @BeforeEach
    void setuo(){
        //inicializo el mock que fue declarado con la anotacion
        MockitoAnnotations.openMocks(this);
        //creo instancia de customers y establezco valores
        customers = new Customers();
        customers.setCustomer_id(1L);
        customers.setCustomer_name("Jhonny");
    }
    //prueba de metodos
    //getAll
    @Test
    void getAll() {
        //creo lista para almacenar customer y lo añado ala lista
        List<Customers> lista = new ArrayList<>();
        lista.add(customers);
        //comportamiento del mock
        when(repositoryCustomer.findAll()).thenReturn(lista);
        //llamo al metodo del servicio
        List<Customers> temp = serviceCustomerImp.findAll();
        assertEquals(1, temp.size());
    }
    //getbyid
    @Test
    void getById(){
        //COMPORTAMIENTO DEL MOCK
        when(repositoryCustomer.findById(1L)).thenReturn(Optional.of(customers));
        //llamo al servicio
        Customers result = serviceCustomerImp.findById(1L);
        assertNotNull(result);
        assertEquals("Jhonny", result.getCustomer_name());
        verify(repositoryCustomer, times(1)).findById(1L);
    }
    //metodo save
    @Test
    void save(){
        Customers Csaved = new Customers();
        Csaved.setCustomer_id(1L);
        Csaved.setCustomer_name("Jhonny");
        //comportamiento del mock
        when(repositoryCustomer.save(customers)).thenReturn(customers);
        //llamo al servicio
        Customers  saved = serviceCustomerImp.save(customers);
        //verificacion
        assertEquals("Jhonny", saved.getCustomer_name());
        assertNotNull(saved);
        verify(repositoryCustomer, times(1)).save(Csaved);
    }
    //metodo update
    @Test
    void update (){
        Customers Ctoupdate = new Customers();
        Ctoupdate.setCustomer_name("Jhonny Actualizado");
        //comportamientos del mock findbyid y save del repo
        when(repositoryCustomer.findById(1L)).thenReturn(Optional.of(customers));
        when(repositoryCustomer.save(customers)).thenReturn(customers);
        //llamo el metodo del servicio y lo guardo
        Customers updated = serviceCustomerImp.update(1L, Ctoupdate);
        assertNotNull(updated);
        assertEquals("Jhonny Actualizado", updated.getCustomer_name());
        verify(repositoryCustomer, times(1)).findById(1L);
        verify(repositoryCustomer, times(1)).save(customers);


    }
    //delete
    @Test
    void delete(){
        //variable que almacena id a eliminar
        Long idTodelete = 1L;
        when(repositoryCustomer.existsById(idTodelete)).thenReturn(true);
        //lamo directamente del servicio
        serviceCustomerImp.delete(idTodelete);
        //verifico que el metodo del repo es llamado unavez
        verify(repositoryCustomer, times(1)).deleteById(idTodelete);
    }

    //delete cliente not found
    @Test
    void delete_not_found() {
        //creo variable para el id a eliminir
        Long idTodelete = 1L;
        //comportamiento del mock cuando llamo al repo devolvera false
        when(repositoryCustomer.existsById(1L)).thenReturn(false);
        //llamo al metodo del servicio y lanzo una excepcion
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            serviceCustomerImp.delete(idTodelete);
        });
        //verifico el mensaje de la excepcion
        assertEquals("Customer not found", exception.getMessage());
        //verifico que el metodo no fue llamado
        verify(repositoryCustomer, never()).deleteById(idTodelete);
    }
}






/*
    //prueba para metodo getall del servicio
    @Test
    void findAll(){
        //creo una lista para alamacenas Custoemrs y añado el cliente simulado
        List<Customers> list = new ArrayList<>();
        list.add(customers);
        //defino el comportamiento del mock:cuando se llame a findAll, devolvera la lista creada
        when(repositoryCustomer.findAll()).thenReturn(list);
        //se llama el metodo findall del servicio y se guarda en la lista temporal
        List<Customers>temp = serviceCustomerImp.findAll();
        //verifica que el tamaño de la lista sea 1 que contenga un cliente
        //asserEquals para comparar
        assertEquals(1, temp.size());

    }
    //prueba byid
    @Test
    void findById(){
        //defino comportamiento del mock: cuando llame el findbyid ,devolvera el cliente simulado
        when(repositoryCustomer.findById(1L)).thenReturn(Optional.of(customers));
        //llamo el metodo findbyid del servicio y se guarda en una variable
        Customers result = serviceCustomerImp.findById(1L);
        //verifica que no sea nulo
        assertNotNull(result);
        //verrifica que el nombre sea igual
        assertEquals("Jhonny", result.getCustomer_name());
        //verifica que el metodo findbyid del repo fue llamado una ve<
        verify(repositoryCustomer, times(1)).findById(1L);
    }
    //metodo save
    @Test
    void save(){
        //creo instancia de clientes que se va guardar
        Customers save = new Customers();
        save.setCustomer_id(1L);
        save.setCustomer_name("Jhonny");
        //comportamiento cuando llamo el metodo del repo devuelve el cliente que se guarda
        when(repositoryCustomer.save(save)).thenReturn(save);
        //llamo el metodo del servicio y y lo guardo
        Customers saved = serviceCustomerImp.save(save);
        //verifico que no sea nulo que el nombre sea igual y sea llamado una vez
        assertNotNull(saved);
        assertEquals("Jhonny", saved.getCustomer_name());
        verify(repositoryCustomer, times(1)).save(save);
    }

}

     */

/*
En resumen, los métodos de Mockito que utilizas en tu prueba son:

@Mock: Para crear objetos simulados.
@InjectMocks: Para inyectar esos mocks en la clase bajo prueba.
MockitoAnnotations.openMocks(this): Para inicializar los mocks.
when(): Para definir el comportamiento del mock.
thenReturn(): Para especificar el valor que debe ser devuelto por el mock.
verify(): Para comprobar si un método del mock fue llamado.
times(): Para especificar cuántas veces se espera que se llame a un método.
Estos métodos son fundamentales para crear pruebas unitarias efectivas y aisladas en Java
utilizando Mockito.


En resumen, los métodos y anotaciones de JUnit que utilizas en tu prueba son:

@Test: Para marcar un método como una prueba.
@BeforeEach: Para ejecutar código antes de cada prueba.
assertEquals(): Para verificar que dos valores sean iguales.
assertNotNull(): Para asegurarse de que un objeto no sea nulo.
Estos elementos son esenciales para estructurar y ejecutar pruebas unitarias efectivas
 en Java utilizando JUnit.
* */


