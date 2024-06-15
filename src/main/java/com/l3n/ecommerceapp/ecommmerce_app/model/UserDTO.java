package com.l3n.ecommerceapp.ecommmerce_app.model;

public class UserDTO {
    private String name;

    // Constructor
    public UserDTO(String name) {
        this.name = name;
    }

    // Getter dan Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
