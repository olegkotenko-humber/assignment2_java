package com.kotenko.inventory;

import com.kotenko.entity.Inventory;
import com.kotenko.entity.Store;

import java.util.List;

public interface InventoryService {
    void clearList();

    List<Inventory> getInventoryList();
    List<Inventory> getInventoryByStoreId(Integer storeId);
    void addToList(Inventory inventory);
    void removeFromList(Inventory inventory);



}
