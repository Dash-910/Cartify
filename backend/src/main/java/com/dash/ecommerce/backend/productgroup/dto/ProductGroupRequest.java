package com.dash.ecommerce.backend.productgroup.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductGroupRequest {

    @NotBlank
    private String name;

    private String description;

    public ProductGroupRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}