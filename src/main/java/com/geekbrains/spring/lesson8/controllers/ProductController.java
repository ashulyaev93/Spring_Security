package com.geekbrains.spring.lesson8.controllers;

import com.geekbrains.spring.lesson8.entities.Product;
import com.geekbrains.spring.lesson8.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson8.services.ProductService;
import com.geekbrains.spring.lesson8.utils.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(
            @PathVariable Long id,
            Model model
    ) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
        model.addAttribute("product", product);
        return "product";
    }


}
