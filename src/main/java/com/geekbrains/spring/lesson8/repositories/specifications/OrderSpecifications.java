package com.geekbrains.spring.lesson8.repositories.specifications;

import com.geekbrains.spring.lesson8.entities.Order;
import com.geekbrains.spring.lesson8.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {
    public static Specification<Order> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), minPrice);  // where order.totalPrice >= minPrice
    }

    public static Specification<Order> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("totalPrice"), maxPrice); // where order.totalPrice <= maxPrice
    }

    public static Specification<Order> customerName(String customerName) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("name")), String.format("%%%s%%", customerName.toLowerCase())); // where order.customer.name like %customerName%
    }

}