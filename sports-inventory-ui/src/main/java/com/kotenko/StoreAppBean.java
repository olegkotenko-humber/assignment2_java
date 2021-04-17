package com.kotenko;

import com.kotenko.entity.Store;
import com.kotenko.interceptor.Logged;
import com.kotenko.store.StoreService;
import lombok.Data;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@SessionScoped
@Named
public class StoreAppBean implements Serializable {


    @Size(min = 5, max = 30)
    @NotEmpty
    private String name;

    @Size(min = 5, max = 50)
    @NotEmpty
    private String location;


    @EJB
    private StoreService storeService;



    public List<Store> getStoreList() {
        return storeService.getStoreList();
    }


    @Logged
    public String addStore() {
        Store store = new Store(name, location);
        Optional<Store> storeExists = storeService.getStoreList().stream().filter(p ->
                p.getName().equals(name) && p.getLocation().equals(location)).findFirst();
        if (!storeExists.isPresent()) {
            storeService.addToList(store);
        }

        return "storeList";
    }

    @Logged
    public String showInventory() {
        return "inventoryList.xhtml";
    }




}
