package coffee.shop.application;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import coffee.shop.domain.FoodCategory;
import coffee.shop.infrastructure.FoodCategoryRepository;

@Singleton
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Inject
    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Override
    public FoodCategory save(FoodCategory foodCategory) {
        return foodCategoryRepository.save(foodCategory);
    }

    @Override
    public Collection<FoodCategory> findAll() {
        return foodCategoryRepository.findAll();
    }

    @Override
    public Optional<FoodCategory> findById(UUID foodCategoryId) {
        return foodCategoryRepository.findById(foodCategoryId);
    }

    @Override
    public Optional<FoodCategory> update(FoodCategory foodCategory) {
        return foodCategoryRepository.update(foodCategory);
    }

}
