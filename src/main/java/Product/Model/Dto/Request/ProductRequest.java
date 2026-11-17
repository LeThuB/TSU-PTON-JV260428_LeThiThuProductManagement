package Product.Model.Dto.Request;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
  @NotBlank(message =  "Ten sp khong duoc de trong")
    private String name;
   @NotNull(message = "Gia san pham khong duoc de trong")
   @Min(value = 1,message = "Gia san pham phai lon hon 0")
    private Double price;

    @NotBlank(message =  "Don vi tien te khong duoc de trong")
    private String currencyUnit;

    @NotBlank(message =  "Ngay nhap hang khong duoc de trong")
    private LocalDateTime importDate;

    @NotNull(message = "Thoi gian bao hanh khong duoc de trong")
    private int warrantyPeriod;
    @NotBlank(message = "Don vi tinh thoi gian bao hanh khong duoc de trong")
    private String warrantyUnit;
    @NotBlank(message =  "Duong dan anh sp khong duoc de trong")
    private String productImage;
    @NotBlank(message =  "Mo ta sp khong duoc de trong")
    private String description;

    private int status;
}
