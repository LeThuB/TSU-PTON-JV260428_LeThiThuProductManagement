package Product.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "productManagementDB")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String currencyUnit;
    @Column(nullable = false)
    private LocalDateTime importDate;
    @Column(nullable = false)
    private int warrantyPeriod;
    @Column(nullable = false)

    private String warrantyUnit;

    @Column(nullable = false)
    private String productImage;
    @Column(nullable = false)
    private String description;
    private int status;
}
