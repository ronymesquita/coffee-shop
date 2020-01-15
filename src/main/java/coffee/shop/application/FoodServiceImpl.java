package coffee.shop.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import coffee.shop.domain.Food;
import coffee.shop.infrastructure.FoodRepository;

@Singleton
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;

    @Inject
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Optional<Food> findById(UUID foodId) {
        return foodRepository.findById(foodId);
    }    

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public void delete(Food food) {
        foodRepository.delete(food);
    }
    
}

