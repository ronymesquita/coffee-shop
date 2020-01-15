package coffee.shop.domain;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategoryDTO {

    private UUID id;
    private String name;

    public static FoodCategoryDTO from(FoodCategory foodCategory) {
        return new FoodCategoryDTO(
            foodCategory.getId(),
            foodCategory.getName()
        );
    }

    public FoodCategory autoConvert() {
        return new FoodCategory(id, name, Set.<Food>of());
    }

}
