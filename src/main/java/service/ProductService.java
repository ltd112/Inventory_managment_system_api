package service;

import dto.ProductDTO;

public interface ProductService {
    ProductDTO rewriteProduct(ProductDTO productDTO); //put

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO, Long id);

    ProductDTO getProductById(Long id);

    void deleteProduct(Long productId);
}
