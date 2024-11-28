package dto;

import entity.Product;
import entity.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    private Long warehouseId;
    private String name;
    private String location;
    private int capacity;
    private Set<Product> products;
    private Set<Shipment> shipments;
}
