package coffee.shop.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.id.UuidStrategy;

import coffee.shop.infrastructure.util.UuidNeo4jConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class FoodCategory implements Serializable {

    private static final long serialVersionUID = -7693177841538522905L;

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    @Convert(UuidNeo4jConverter.class)
    private UUID id;

    private String name;

    @Relationship("CATEGORIZES")
    private Set<Food> foods;

}
