package icet.edu.drm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private String id;
    private String name;
    private String size;
    private int qty;
    private double price;
    private String supId;



}
