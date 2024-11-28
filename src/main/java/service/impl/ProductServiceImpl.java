package service.impl;

import dto.ProductDTO;
import entity.Product;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO rewriteProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getProductId()).
                orElseThrow(()-> new ResourceNotFoundException("Product", "id", productDTO.getProductId()));
        product.setCategory(productDTO.getCategory());
        product.setId(productDTO.getProductId());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        productRepository.save(product);
        return  productDTO;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        return mapToDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));

        product.setCategory(productDTO.getCategory());
        product.setId(productDTO.getProductId());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        productRepository.save(product);


        return mapToDTO(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).
                orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);

    }
    private Product mapToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        return product;
    }
    private ProductDTO mapToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory());
        productDTO.setProductId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }
}
