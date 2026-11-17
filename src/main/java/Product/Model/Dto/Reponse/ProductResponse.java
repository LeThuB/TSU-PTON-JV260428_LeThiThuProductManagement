package Product.Model.Dto.Reponse;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String currencyUnit;
    private int warrantyPeriod;
    private String description;
}
