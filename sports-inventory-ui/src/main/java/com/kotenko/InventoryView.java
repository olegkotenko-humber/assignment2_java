package com.kotenko;

import com.kotenko.entity.Inventory;
import com.kotenko.entity.Store;
import com.kotenko.interceptor.Logged;
import com.kotenko.inventory.InventoryService;
import com.kotenko.store.StoreService;
import lombok.Data;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@SessionScoped
public class InventoryView implements Serializable {

    @Size(min = 5, max = 30)
    @NotEmpty
    private String name;

    @Size(min = 5, max = 50)
    @NotEmpty
    private String sport;
    private Integer quantity;
    private Double price;
    private String storeName;

    @EJB
    private StoreService storeService;
    @EJB
    private InventoryService inventoryService;


    public List<Inventory> getInventoryList() {
        return inventoryService.getInventoryList();
    }

    public List<Inventory> getInventoryByStoreId(Integer storeId) {
        return inventoryService.getInventoryByStoreId(storeId);
    }

    @Logged
    public String addInventory() {
        Store store = storeService.getStoreByName(storeName);
        Inventory inventory = new Inventory(name,sport,quantity,price,store);
        inventoryService.addToList(inventory);
        return "inventoryList";
    }


}
