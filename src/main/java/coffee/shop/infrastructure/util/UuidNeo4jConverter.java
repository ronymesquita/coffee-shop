package coffee.shop.infrastructure.util;

import java.util.UUID;

import org.neo4j.ogm.typeconversion.AttributeConverter;

public class UuidNeo4jConverter implements AttributeConverter<UUID, String> {

    @Override
    public String toGraphProperty(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID toEntityAttribute(String uuid) {
        return UUID.fromString(uuid);
    }

    
}
