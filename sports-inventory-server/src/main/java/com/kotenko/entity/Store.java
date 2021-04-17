package com.kotenko.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT r FROM Store r")
@NamedQuery(name = "Store.clearAll", query = "DELETE FROM Store")
@NamedQuery(name = "Store.getByStoreName", query = "SELECT p from Store p where p.name = :storeName")
public class Store implements Comparable<Store>,Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String location;


    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    private List<Inventory> inventoryList;

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public int compareTo(Store o) {
        return name.compareTo(o.name);
    }
}
