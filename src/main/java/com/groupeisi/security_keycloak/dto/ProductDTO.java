package com.groupeisi.security_keycloak.dto;

public class ProductDTO {
    private Long id;
    private String libelle;
    private String name;

    public ProductDTO() {}

    public ProductDTO(Long id, String ref, String name) {
        this.id = id;
        this.libelle = ref;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setLibelle(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setRef(String ref) {
        this.libelle = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
