package coffee.shop.infrastructure;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Singleton;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import coffee.shop.domain.FoodCategory;

@Singleton
public class FoodCategoryRepositoryImpl implements FoodCategoryRepository {

    private final Session databaseSession;

    public FoodCategoryRepositoryImpl() {
        var neo4jConfiguration = new Configuration.Builder()
                .uri("bolt://localhost")
                .useNativeTypes()
                .build();
        var neo4jSessionFactory = new SessionFactory(neo4jConfiguration, "coffee.shop.domain");
        databaseSession = neo4jSessionFactory.openSession();
    }

    @Override
    public FoodCategory save(FoodCategory foodCategory) {
        databaseSession.save(foodCategory);
        return findById(foodCategory.getId()).get();
    }

    @Override
    public Collection<FoodCategory> findAll() {
        return databaseSession.loadAll(FoodCategory.class);
    }

    public Optional<FoodCategory> findById(UUID foodCategoryId) {
        return Optional.ofNullable(
            databaseSession.load(FoodCategory.class, foodCategoryId)
        );
    }

    @Override
    public Optional<FoodCategory> update(FoodCategory foodCategory) {
        if (findById(foodCategory.getId()).isPresent()) {
            databaseSession.save(foodCategory);
            return findById(foodCategory.getId());
        }

        return Optional.ofNullable(null);
    }
    
}
