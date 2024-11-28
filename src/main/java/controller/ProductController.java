package controller;

import dto.ProductDTO;
import exception.BadRequestException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "productId") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> rewriteProduct(@RequestBody ProductDTO productDTO){
        ProductDTO rewriteProduct = productService.rewriteProduct(productDTO);
        return new ResponseEntity<>(rewriteProduct, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        if(productDTO.getProductId() == null){
            log.error("cant have an ID {}", productDTO);
            throw new BadRequestException(ProductController.class.getSimpleName(), "Id");
        }
        ProductDTO product = productService.createProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable(name = "productId") Long id){
        ProductDTO product = productService.updateProduct(productDTO, id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("deleted complete", HttpStatus.OK);
    }
}
