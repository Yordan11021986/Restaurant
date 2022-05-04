package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private Collection<Beverages> entities;

    public BeverageRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void add(Beverages entity) {
        this.entities.add(entity);
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return this.entities
                .stream()
                .filter(d -> d.getName().equals(drinkName) && d.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return this.entities;
    }
}
