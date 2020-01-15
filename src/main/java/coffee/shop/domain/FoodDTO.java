package coffee.shop.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    private UUID id;
    private String name;
    private Set<Price> prices = new HashSet<>();

    public static FoodDTO from(Food food) {
        var foodId = Optional.ofNullable(food.getId()).orElse(UUID.randomUUID());
        return new FoodDTO(
            foodId,
            food.getName(),
            food.getPrices()
        ); 
    }

    public Food autoConvert() {
        var foodId = Optional.ofNullable(id).orElse(UUID.randomUUID());
        return new Food(
            foodId,
            name,
            prices,
            Set.<FoodCategory>of(),
            Set.<Stock>of()
        );
    }

}
