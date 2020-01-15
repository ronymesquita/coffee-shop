package coffee.shop.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import coffee.shop.domain.Food;

public interface FoodService {

    Food save(Food food);
    Optional<Food> findById(UUID foodId);
    List<Food> findAll();
    void delete(Food food);

}
