package dto;

import entity.Product;
import entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDTO {
    private Long shipmentId;
    private String status;
    private String tracking_number;
    private Set<Product> products;
    private Set<Warehouse> warehouses;
}
