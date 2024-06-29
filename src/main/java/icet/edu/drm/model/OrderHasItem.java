package icet.edu.drm.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHasItem {

    private Integer id;
    
    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double discount;

}
