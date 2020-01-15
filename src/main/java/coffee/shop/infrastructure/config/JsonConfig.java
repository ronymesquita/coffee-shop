package coffee.shop.infrastructure.config;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;

import org.zalando.jackson.datatype.money.MoneyModule;

import coffee.shop.infrastructure.util.UuidDeserializer;
import coffee.shop.infrastructure.util.UuidSerializer;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

@Factory
public class JsonConfig {

    @Bean
    public Module jacksonDatatypeMoney() {
        return new MoneyModule();
    }
    
    @Bean
    public JsonSerializer<UUID> uuidSerializer() {
        return new UuidSerializer();
    }

    @Bean
    public JsonDeserializer<UUID> uuidDeserializer() {
        return new UuidDeserializer();
    }

}
