package dto;

import entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Category category;
    private Set<String> shipmentIds;
    private Set<String> warehouseIds;
}
