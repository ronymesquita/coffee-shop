package coffee.shop.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import coffee.shop.domain.Food;

@Singleton
public class FoodRepositoryImpl implements FoodRepository {

    private final Session databaseSession;

    // @Inject
    public FoodRepositoryImpl() {
        var neo4jConfiguration = new Configuration.Builder()
                .uri("bolt://localhost")
                .useNativeTypes()
                .build();
        var neo4jSessionFactory = new SessionFactory(neo4jConfiguration, "coffee.shop.domain");
        databaseSession = neo4jSessionFactory.openSession();
    }

    @Override
    public Optional<Food> findById(UUID id) {
        return Optional.ofNullable(
            databaseSession.load(Food.class, id)
        );
    }

    @Override
    public List<Food> findAll() {
        return databaseSession.loadAll(Food.class)
            .stream()
            .collect(Collectors.toList());
    }

    public void delete(Food food) {
        databaseSession.delete(food);
    }

    @Override
    public Food save(Food food) {
        databaseSession.save(food);
        return findById(food.getId()).get();
    }

}
