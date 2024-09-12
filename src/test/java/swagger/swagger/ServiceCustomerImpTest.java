package swagger.swagger;
//importo anoaciones JUnito para lacreacion de pruebas
import org.junit.jupiter.api.*;
//importo anotaciones mochito que permite la cracion de objetos simulados
import org.mockito.*;
import swagger.swagger.model.Customers;
import swagger.swagger.repository.RepositoryCustomer;
import swagger.swagger.service.ServiceCustomerImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//importo afirmaciones de JUnit que se utilizan para verificar resultados assertions
import static  org.junit.jupiter.api.Assertions.*;
//importo los metodos d mockito para simular comportamientos de metodos
import static org.mockito.Mockito.*;

public class ServiceCustomerImpTest {
    //variable para almacenar la instancia de customer que se usara en las pruebas
    private Customers customers;
    //mock indica que este es un objeto simulado
    @Mock
    //simulacion del repositorio que maneja las operaciones de clientes
    private RepositoryCustomer repositoryCustomer;

    //indica que se inyecta el mock en este objeto
    @InjectMocks
    //servicio que se esta probando ,que utiliza el repositorio
    private ServiceCustomerImp serviceCustomerImp;

    //ESTE METODO se ejecuta antes de cada prueba para preparar el entorno
    @BeforeEach
    void setup(){
        //inicializa el mock que se han declarado con la anotacion mock
        MockitoAnnotations.openMocks(this);
        //creo una instancia de Customers que se utilizara en las pruebas
        customers = new Customers();
        //establezco el valor para el cliente simulado
        customers.setCustomer_id(1L);
        customers.setCustomer_name("Jhonny");

    }
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
}

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
