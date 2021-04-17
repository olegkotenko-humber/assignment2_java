package com.kotenko.store;

import com.kotenko.entity.Store;

import java.util.List;

public interface StoreService {
    void clearList();

    List<Store> getStoreList();

    void addToList(Store store);
    void removeFromList(Store store);
    Store getStoreByName(String name);
}
