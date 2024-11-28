package entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "category")
public class Category implements Serializable {
    @Id
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;


}
