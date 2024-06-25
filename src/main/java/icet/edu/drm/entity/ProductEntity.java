package icet.edu.drm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@Table(name = "product")
public class ProductEntity {

    @Id
    private String id;

    private String name;
    private String size;
    private int qty;
    @Column(name = "price")
    private double price;
    @Column(name = "supId")
    private String supId;

}