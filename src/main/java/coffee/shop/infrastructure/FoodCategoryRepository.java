package coffee.shop.infrastructure;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import coffee.shop.domain.FoodCategory;

public interface FoodCategoryRepository {

    Collection<FoodCategory> findAll();
    Optional<FoodCategory> findById(UUID foodCategoryId);
    FoodCategory save(FoodCategory foodCategory);
    Optional<FoodCategory> update(FoodCategory foodCategory); 

}
