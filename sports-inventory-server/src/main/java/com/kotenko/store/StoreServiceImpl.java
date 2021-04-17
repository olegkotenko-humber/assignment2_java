package com.kotenko.store;

import com.kotenko.entity.Store;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(StoreService.class)
public class StoreServiceImpl implements StoreService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void clearList() {
        Query deleteFromStore = em.createNamedQuery("Store.clearAll");
        deleteFromStore.executeUpdate();
    }

    @Override
    public List<Store> getStoreList() {
        List<Store> storeList = em.createNamedQuery("Store.findAll", Store.class)
                .getResultList();
        return storeList.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addToList(Store store) {em.persist(store);}

    @Override
    public void removeFromList(Store store) {
        Store correspondingStore = em.createNamedQuery("Store.getByStoreName", Store.class)
                .setParameter("storeName", store.getName())
                .getSingleResult();
        em.remove(correspondingStore);
    }

    @Override
    public Store getStoreByName(String name) {
        Store correspondingStore = em.createNamedQuery("Store.getByStoreName", Store.class)
                .setParameter("storeName", name)
                .getSingleResult();
        return correspondingStore;
    }

//    @Override
//    public void addInventory(String storeName, Inventory inventory) {
//        Inventory correspondingInventory = em.createNamedQuery("Player.getByUserName", Inventory.class)
//                .setParameter("userName", userName)
//                .getSingleResult();
//        store.setInventory(correspondingInventory);
//        em.persist(store);
//    }
//
//    @Override
//    public List<Inventory> getAllInventories() {
//        return null;
//    }

}
