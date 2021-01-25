package com.geekbrains.spring.lesson8.utils;

import com.geekbrains.spring.lesson8.entities.User;
import com.geekbrains.spring.lesson8.repositories.specifications.UserSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class UserFilter {
    private Specification<User> spec;
    private String filterDefinition;

    public UserFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String customerName = params.get("customerName");
        if (customerName != null && !customerName.isBlank()) {
            spec = spec.and(UserSpecifications.customerName(customerName));
            filterDefinitionBuilder.append("&title=").append(customerName);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(UserSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(UserSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<User> getSpec() {
        return spec;
    }

    public void setSpec(Specification<User> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}

