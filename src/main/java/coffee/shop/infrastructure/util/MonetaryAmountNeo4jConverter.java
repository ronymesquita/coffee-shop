package coffee.shop.infrastructure.util;

import java.math.BigDecimal;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.neo4j.ogm.typeconversion.AttributeConverter;

public class MonetaryAmountNeo4jConverter implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String toGraphProperty(MonetaryAmount monetaryAmount) {
        return monetaryAmount.toString();
    }

    @Override
    public MonetaryAmount toEntityAttribute(String monetaryAmountText) {
        var currencyUnitText = monetaryAmountText.split("\\s")[0];
        var monetaryAmount = new BigDecimal(monetaryAmountText.split("\\s")[1]);
        return Money.of(monetaryAmount, currencyUnitText);
    }

    
}
