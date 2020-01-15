package coffee.shop.infrastructure.util;

import java.util.Optional;
import java.util.UUID;

import javax.inject.Singleton;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

@Singleton
public class UuidToStringConverter implements TypeConverter<UUID, String> {

    @Override
    public Optional<String> convert(UUID uuid, Class<String> targetType, ConversionContext conversionContext) {
        return Optional.of(uuid.toString());
    }
    
}
