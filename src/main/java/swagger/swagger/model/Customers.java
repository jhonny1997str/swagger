package swagger.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@ApiModel(description = "Modelo que representa a un cliente")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID único del cliente", example = "1", required = true)
    private Long customer_id;

    @ApiModelProperty(value = "Nombre del cliente", example = "Jhonny Tapasco", required = true)
    private String customer_name;
}
