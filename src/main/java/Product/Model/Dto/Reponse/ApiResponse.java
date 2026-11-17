package Product.Model.Dto.Reponse;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
   private int status;
   private  String message;
   private T data;
   private LocalDateTime timestamp;

}
