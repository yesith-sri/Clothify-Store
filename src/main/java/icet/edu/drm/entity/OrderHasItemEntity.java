package icet.edu.drm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_details")
@Table(name = "order_details")
public class OrderHasItemEntity {

    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double discount;

}
