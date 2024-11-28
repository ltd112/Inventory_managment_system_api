package entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter
@Entity

@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseid;
    private String name;
    private String location;
    private int capacity;

    @ManyToMany(mappedBy = "warehouses")
    private Set<Product> product;

    @ManyToMany(mappedBy = "warehouses")
    private Set<Shipment> shipment;
}
