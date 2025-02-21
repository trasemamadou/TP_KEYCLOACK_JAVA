package com.groupeisi.security_keycloak.controller;

import com.groupeisi.security_keycloak.dto.ProductDTO;
import com.groupeisi.security_keycloak.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String libelle, @RequestParam String name) {
        ProductDTO productDTO = new ProductDTO(null, libelle, name);
        productService.addProduct(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }

    // ✅ Méthode pour mettre à jour un produit
    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam String libelle, @RequestParam String name) {
        ProductDTO productDTO = new ProductDTO(id, libelle, name);
        productService.updateProduct(productDTO);
        return "redirect:/products";
    }

    // ✅ Méthode pour supprimer un produit
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
