package Product.Controller;

import Product.Model.Dto.Reponse.ApiResponse;
import Product.Model.Dto.Reponse.ProductResponse;
import Product.Model.Dto.Request.ProductRequest;
import Product.Model.Entity.Product;
import Product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    //xem danh sach
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProduct(){
        List<ProductResponse> list = productService.getProduct();
        ApiResponse<List<ProductResponse>>response = ApiResponse.<List<ProductResponse>>builder()
                .status(200)
                .message("Danh sach sna pham")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //them moi
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody ProductRequest productRequest){
        Product product = productService.createProduct(productRequest);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .status(200)
                .message("Them moi san pham thanh cong")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //cap nhat  theo id
    @PutMapping("/{id}")

    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest){
        Product product = productService.updateProduct(id,productRequest);
        ApiResponse<Product>response = ApiResponse.<Product>builder()
                .status(200)
                .message("Cap nhat san pham thanh cong")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //tim kiem theo ten
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse< List<Product>>> getProductByName(@RequestParam("name") String name) {
        List<Product> list = productService.getProductByName(name);
        ApiResponse<List<Product>> response = ApiResponse.<List<Product>>builder()
                .status(200)
                .message("Danh sach sp theo ten")
                .data(list)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //tim kiem theo status
    @GetMapping("/search/status")
    public ResponseEntity<ApiResponse<List<Product>>> getByStatus(@RequestParam("status") int status){
        List<Product> list = productService.getProductByStatus(status);
        ApiResponse<List<Product>> response = ApiResponse.<List<Product>>builder()
                .status(200)
                .message("Danh sach san pham theo Status")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //thay doi trang thai
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Product>> updateStatus(@PathVariable Long id,@RequestParam("status")int status){
        Product updateStatus = productService.changeStatus(id,status);
        ApiResponse<Product>response = ApiResponse.<Product>builder()
                .status(200)
                .message("Thay doi trang thai thanh cong")
                .data(updateStatus)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
