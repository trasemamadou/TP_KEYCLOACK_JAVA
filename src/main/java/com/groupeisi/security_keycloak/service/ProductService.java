package com.groupeisi.security_keycloak.service;

import com.groupeisi.security_keycloak.dto.ProductDTO;
import com.groupeisi.security_keycloak.entity.Product;
import com.groupeisi.security_keycloak.mapper.ProductMapper;
import com.groupeisi.security_keycloak.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));
        return productMapper.toDTO(product);
    }

    // ✅ Méthode pour mettre à jour un produit
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));
        existingProduct.setLibelle(productDTO.getLibelle());
        existingProduct.setName(productDTO.getName());
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    // ✅ Méthode pour supprimer un produit
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
