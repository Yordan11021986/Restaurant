package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.ArrayList;
import java.util.Collection;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {
    private Collection<HealthyFood> entities;

    public HealthFoodRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void add(HealthyFood entity) {
        this.entities.add(entity);
    }

    @Override
    public HealthyFood foodByName(String name) {
        return this.entities
                .stream()
                .filter(h -> h.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return this.entities;
    }
}
