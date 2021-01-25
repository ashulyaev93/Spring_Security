package com.geekbrains.spring.lesson8.repositories.specifications;

import com.geekbrains.spring.lesson8.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), minPrice);  // where order.totalPrice >= minPrice
    }

    public static Specification<User> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("totalPrice"), maxPrice); // where order.totalPrice <= maxPrice
    }

    public static Specification<User> customerName(String customerName) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("name")), String.format("%%%s%%", customerName.toLowerCase())); // where order.customer.name like %customerName%
    }
}