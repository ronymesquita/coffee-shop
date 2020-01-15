package coffee.shop.infrastructure.util;

import java.util.Optional;
import java.util.UUID;

import javax.inject.Singleton;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

@Singleton
public class StringToUuidConverter implements TypeConverter<String, UUID> {

    @Override
    public Optional<UUID> convert(String uuidText, Class<UUID> targetType, ConversionContext conversionContext) {
        try {
            return Optional.of(UUID.fromString(uuidText));
        } catch (Exception exception) {
            conversionContext.reject(uuidText, exception);
            return Optional.empty();
        }
    }
    
}
