package coffee.shop.application;

import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import coffee.shop.domain.FoodCategory;
import coffee.shop.domain.FoodCategoryDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Controller("/foods-categories")
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @Inject
    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @Get
    public Flowable<MutableHttpResponse<Collection<FoodCategory>>> findAll() {
        var foodCategories = foodCategoryService.findAll();
        return Flowable.just(HttpResponse.ok(foodCategories));
    }

    @Get("/{foodCategoryIdSingle}")
    public Single<MutableHttpResponse<FoodCategoryDTO>> findById(Single<String> foodCategoryIdSingle) {
        return foodCategoryIdSingle
            .map(UUID::fromString)
            .map(foodCategoryService::findById)
            .map(foodCategory -> {
                if (foodCategory.isPresent()) {
                    var foodCategoryDTO = FoodCategoryDTO.from(foodCategory.get());
                    return HttpResponse.ok(foodCategoryDTO);
                }

                return HttpResponse.notFound();
            });
    }

    @Post
    public Flowable<MutableHttpResponse<FoodCategory>> save(@Body Single<FoodCategoryDTO> foodCategoryDTOSingle) {
        return foodCategoryDTOSingle
            .map(FoodCategoryDTO::autoConvert)
            .map(foodCategoryService::save)
            .map(HttpResponse::created)
            .toFlowable();
    }

    @Put("/{foodCategoryId}")
    public Single<MutableHttpResponse<FoodCategory>> update(
        @PathVariable UUID foodCategoryId,
        @Body Single<FoodCategory> foodCategorySingle
    ) {
        return foodCategorySingle
            .map(foodCategory -> {
                var updatedFoodCategory = foodCategoryService.update(foodCategory);
                if (updatedFoodCategory.isPresent()) {
                    return HttpResponse.ok(updatedFoodCategory.get());
                }

                return HttpResponse.notFound();
            });
    }
}
