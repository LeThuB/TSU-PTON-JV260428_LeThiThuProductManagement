package Product.Service;

import Product.Exception.DuplicateResourceEx;
import Product.Exception.ResourceNotFoundEx;
import Product.Model.Dto.Reponse.ProductResponse;
import Product.Model.Dto.Request.ProductRequest;
import Product.Model.Entity.Product;
import Product.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    //xem danh sach
    public List<ProductResponse> getProduct(){
      List<Product> products = productRepository.findAll();
      return products.stream().map(product -> new ProductResponse(
              product.getId(),
              product.getName(),
              product.getPrice(),
              product.getCurrencyUnit(),
              product.getWarrantyPeriod(),
              product.getDescription()
      )).toList();
    }
//them moi sp
    public Product createProduct(ProductRequest productRequest){
        if(productRepository.existsByName(productRequest.getName())){
            throw new DuplicateResourceEx("Ten san pham da ton tai");
        }
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .currencyUnit(productRequest.getCurrencyUnit())
                .importDate(productRequest.getImportDate())
                .warrantyPeriod(productRequest.getWarrantyPeriod())
                .warrantyUnit(productRequest.getWarrantyUnit())
                .productImage(productRequest.getProductImage())
                .description(productRequest.getDescription())
                .status(1).build();
        return productRepository.save(product);
    }

    //cap nhat theo id
    public Product updateProduct(Long id,ProductRequest productRequest){
        Product product = productRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundEx("Khong tim thay san pham co ID: "+ id));
        if(productRepository.existsByNameAndIdNot(productRequest.getName(), id)){
            throw new DuplicateResourceEx("Ten sp da ton tai ");
        }
        Product updateProduct = Product.builder()
                .id(product.getId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .currencyUnit(productRequest.getCurrencyUnit())
                .importDate(productRequest.getImportDate())
                .warrantyPeriod(productRequest.getWarrantyPeriod())
                .warrantyUnit(productRequest.getWarrantyUnit())
                .productImage(productRequest.getProductImage())
                .description(productRequest.getDescription())
                .status(productRequest.getStatus())
                .build();
        return productRepository.save(updateProduct);



}

//tim kiem theo ten
    public List<Product> getProductByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    //tim kiem theo trang thai
    public List<Product> getProductByStatus(int status){
        return productRepository.findByStatus(status);
    }

    //thay doi status
    public Product changeStatus(Long id, int newStatus){
        Product product = productRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundEx("Khong tim thay san pham co ID :"+ id));
        if (product.getStatus() ==2 ){ throw new RuntimeException("Khong the thay doi san pham co trang thai bang 2")
        ;}
        else {product.setStatus(newStatus);}
        return productRepository.save(product);
    }
}
