package coffee.shop.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import coffee.shop.domain.Food;

public interface FoodRepository {

    Food save(Food food);
    Optional<Food> findById(UUID foodId);
    List<Food> findAll();
    void delete(Food food);
    
}
