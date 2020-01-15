package coffee.shop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.id.UuidStrategy;

import coffee.shop.infrastructure.util.MonetaryAmountNeo4jConverter;
import coffee.shop.infrastructure.util.UuidNeo4jConverter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NodeEntity
public class Price implements Serializable  {

    private static final CurrencyUnit DEFAULT_CURRENT_UNIT = Monetary.getCurrency("BRL");
    private static final MonetaryAmount DEFAULT_MONETARY_AMOUNT = Money.of(
        BigDecimal.ZERO,
        DEFAULT_CURRENT_UNIT);

    private static final OffsetDateTime DEFAULT_START_DATE = OffsetDateTime.now();
    private static final OffsetDateTime DEFAULT_END_DATE = OffsetDateTime.now().plusMonths(1);

    public static final Price ZERO = new Price(BigDecimal.ZERO);

    private static final long serialVersionUID = -5350674814766337603L;

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    @Convert(UuidNeo4jConverter.class)
    private UUID id;

    @Convert(MonetaryAmountNeo4jConverter.class)
    private MonetaryAmount monetaryAmount;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    public Price() {
        monetaryAmount = DEFAULT_MONETARY_AMOUNT;
        configureDefaultDates();
    }

    public Price(BigDecimal monetaryAmount) {
        this.monetaryAmount = Money.of(monetaryAmount, DEFAULT_CURRENT_UNIT);
        configureDefaultDates();
    }

    private void configureDefaultDates() {
        startDate = DEFAULT_START_DATE;
        endDate = DEFAULT_END_DATE;
    }

}
