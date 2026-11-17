package Product.Exception;

import Product.Model.Dto.Reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleEx {

    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ApiResponse<String>> notFound(ResourceNotFoundEx ex){
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(404)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> notValid(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        ApiResponse<Map<String,String>> response = ApiResponse.<Map<String, String>>builder()
                .status(400)
                .message("Loi validate")
                .data(errors)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(DuplicateResourceEx.class)
    public ResponseEntity<ApiResponse<String>> duplicateEx(DuplicateResourceEx ex){
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(409)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> ex(Exception ex){
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(500)
                .message("Loi he thong")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }
}
