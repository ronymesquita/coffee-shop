package coffee.shop.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Stock implements Serializable {

    private static final long serialVersionUID = 8503104433340678828L;

    @Id
    @GeneratedValue
    private UUID id;

    private Integer currentAmount;
    private Integer minimumAmount;
    private ZonedDateTime entryDateTime;
    private OffsetDateTime validity;

    @Relationship(type = "STOCK", direction = Relationship.OUTGOING)
    Set<Food> foods = new HashSet<>();

}
