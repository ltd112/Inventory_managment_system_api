package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @Column(name = "shipment_id")
    private long shipmentId;
    private String status;
    @Column(name = "tracking_number")
    private String trackingNumber;

    @ManyToMany(mappedBy = "shipments")
    private Set<Product> products;

    @ManyToMany
    @JoinTable(
            name = "product_warehouse",
            joinColumns = @JoinColumn(name = "shipment_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private Set<Warehouse> warehouses;
}
