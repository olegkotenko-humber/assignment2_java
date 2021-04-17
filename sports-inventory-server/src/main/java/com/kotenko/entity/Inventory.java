package com.kotenko.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Inventory.findAll", query = "SELECT p FROM Inventory p")
@NamedQuery(name = "Inventory.getByInventoryName", query = "SELECT p from Inventory p where p.name = :inventoryName")
@NamedQuery(name = "Inventory.findAllByStoreId", query = "SELECT p from Inventory p where p.store = :storeId")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
public class Inventory implements Comparable<Inventory>, Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public Inventory(String name, String sport, Integer quantity, Double price, Store store) {
        this.name = name;
        this.sport = sport;
        this.quantity = quantity;
        this.price = price;
        this.store = store;
    }

    @Column(unique = true)
    private String name;
    private String sport;
    private Integer quantity;
    private Double price;
    private Date dateUpdated;
    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;

    @PrePersist
    void createdAt() {
        this.dateUpdated = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.dateUpdated = new Date();
    }

    @Override
    public int compareTo(Inventory o) {
        return dateUpdated.compareTo(o.dateUpdated);
    }
}
