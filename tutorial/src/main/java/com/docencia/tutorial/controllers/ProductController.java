package com.docencia.tutorial.controllers;

import com.docencia.tutorial.models.Comment;
import com.docencia.tutorial.models.Product;
import com.docencia.tutorial.repositories.CommentRepository;
import com.docencia.tutorial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("title", "Products - Online Store");
        model.addAttribute("subtitle", "List of products");
        model.addAttribute("products", products);
        return "product/index"; // Retorna la vista product/index.html (Thymeleaf)
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("title", product.getName() + " - Online Store");
        model.addAttribute("subtitle", product.getName() + " - Product information");
        model.addAttribute("product", product);
        model.addAttribute("comment", new Comment()); // Para el formulario
        return "product/show"; // Retorna la vista product/show.html
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping
    public String save(@ModelAttribute Product product) {
        if (product.getName() == null || product.getName().isEmpty() ||
                product.getPrice() == null) {
            throw new RuntimeException("Name and Price are required");
        }
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/comments")
    public String saveComment(@PathVariable Long id, @ModelAttribute Comment comment) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            comment.setProduct(product);
            commentRepository.save(comment);
        }
        return "redirect:/products/" + id;
    }
}
