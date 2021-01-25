package com.geekbrains.spring.lesson8.services;

import com.geekbrains.spring.lesson8.entities.Category;
import com.geekbrains.spring.lesson8.entities.Product;
import com.geekbrains.spring.lesson8.repositories.CategoryRepository;
import com.geekbrains.spring.lesson8.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoryByCode(String code){
        return categoryRepository.findCategoriesByCode(code);
    }

    public Page<Product> getProductsByCategory(String code, Pageable pageable){
        return productRepository.findProductByCategoryCode(code, pageable);
    }
}
