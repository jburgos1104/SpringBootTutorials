package com.docencia.tutorial.controllers;

import com.docencia.tutorial.models.Product;
import com.docencia.tutorial.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository; // Inyección del repositorio

    @GetMapping
    public String index(HttpSession session, Model model) {
        // Obtener productos del carrito almacenados en la sesión
        Map<Integer, Integer> cartProductData = (Map<Integer, Integer>) session.getAttribute("cart_product_data");
        Map<Integer, Product> cartProducts = new HashMap<>();

        // Cargar todos los productos desde la base de datos
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); // Agregar productos al modelo

        if (cartProductData != null) {
            // Agrega a cartProducts los productos presentes en el carrito
            for (Integer id : cartProductData.keySet()) {
                // Buscar el producto en la base de datos
                Product product = productRepository.findById(Long.valueOf(id)).orElse(null);
                if (product != null) {
                    cartProducts.put(id, product);
                }
            }
        }

        model.addAttribute("title", "Cart - Online Store");
        model.addAttribute("subtitle", "Shopping Cart");
        model.addAttribute("cartProducts", cartProducts);
        return "cart/index";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Integer id, HttpSession session) {
        // Recuperar el carrito de la sesión o crear uno nuevo si es nulo
        Map<Integer, Integer> cartProductData = (Map<Integer, Integer>) session.getAttribute("cart_product_data");
        if (cartProductData == null) {
            cartProductData = new HashMap<>();
        }
        // Agrega el producto al carrito (aquí se usa el id como clave y valor)
        cartProductData.put(id, id);
        session.setAttribute("cart_product_data", cartProductData);
        return "redirect:/cart";
    }

    @GetMapping("/removeAll")
    public String removeAll(HttpSession session) {
        // Elimina el atributo del carrito de la sesión
        session.removeAttribute("cart_product_data");
        return "redirect:/cart";
    }
}