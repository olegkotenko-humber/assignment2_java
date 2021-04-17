package com.kotenko.inventory;

import com.kotenko.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void clearList() {
        Query deleteFromPlayer = em.createNamedQuery("Inventory.clearAll");
        deleteFromPlayer.executeUpdate();
    }

    public List<Inventory> getAllByBuilder() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Inventory> query = builder.createQuery(Inventory.class);
        Root<Inventory> from = query.from(Inventory.class);
        TypedQuery<Inventory> q = em.createQuery(query.select(from));
        return q.getResultList();
    }

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList = em.createNamedQuery("Inventory.findAll", Inventory.class)

                .getResultList();

        return inventoryList.stream()
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public List<Inventory> getInventoryByStoreId(Integer storeId) {
        List<Inventory> inventoryList = em.createNamedQuery("Inventory.findAllByStoreId", Inventory.class)
                .setParameter("storeId", storeId)
                .getResultList();

        return inventoryList.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addToList(Inventory inventory) {
        em.persist(inventory);
    }

    @Override
    public void removeFromList(Inventory inventory) {
        Inventory correspondingInventory = em.createNamedQuery("Inventory.getByInventoryName", Inventory.class)
                .setParameter("inventoryName", inventory.getName())
                .getSingleResult();
        em.remove(correspondingInventory);
    }


}
