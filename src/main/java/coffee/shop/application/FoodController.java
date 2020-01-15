package coffee.shop.application;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import coffee.shop.domain.FoodDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Controller("/foods")
public class FoodController {

    private final FoodService foodService;

    @Inject
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Get
    public Single<MutableHttpResponse<Collection<FoodDTO>>> findAll() {
        var foods = foodService.findAll();
        return Single.just(foods)
            .map(allFoods -> 
                allFoods.stream()
                    .map(FoodDTO::from)
                    .collect(Collectors.toSet()))
            .map(HttpResponse::ok);
    }

    @Get("/{foodIdSingle}")
    public Single<MutableHttpResponse<FoodDTO>> findById(Single<String> foodIdSingle) {
        return foodIdSingle
            .map(UUID::fromString)
            .map(foodService::findById)
            .map(food -> {
                if (food.isPresent()) {
                    var foodDTO = FoodDTO.from(food.get());
                    return HttpResponse.ok(foodDTO);
                }

                return HttpResponse.notFound();
            });
    }

    @Post
    public Flowable<MutableHttpResponse<FoodDTO>> save(@Body Single<FoodDTO> foodDTOSingle) {
        return foodDTOSingle
            .map(FoodDTO::autoConvert)
            .map(foodService::save)
            .map(FoodDTO::from)
            .map(HttpResponse::created)
            .toFlowable();
    }

    @Delete("/{foodIdSingle}")
    public Single<MutableHttpResponse<Void>> deleteById(Single<String> foodIdSingle) {
        return foodIdSingle
            .map(UUID::fromString)
            .map(foodService::findById)
            .map(food -> {
                if (food.isPresent()) {
                    foodService.delete(food.get());
                    return HttpResponse.noContent();
                }

                return HttpResponse.notFound();
            });
    }
}
