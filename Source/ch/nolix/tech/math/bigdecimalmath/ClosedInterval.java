package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IClosedInterval;

public final class ClosedInterval implements IClosedInterval {

  private final BigDecimal min;

  private final BigDecimal max;

  public ClosedInterval(final BigDecimal min, final BigDecimal max) {

    GlobalValidator.assertThat(min).thatIsNamed(LowerCaseVariableCatalog.MINIMUM).isNotNull();
    GlobalValidator.assertThat(max).thatIsNamed(LowerCaseVariableCatalog.MAXIMUM).isNotSmallerThan(min);

    final var decimalPlaces = GlobalCalculator.getMax(min.scale(), max.scale());
    this.min = min.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.max = max.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ClosedInterval(final BigDecimal min, final BigDecimal max, final int decimalPlaces) {

    GlobalValidator.assertThat(min).thatIsNamed(LowerCaseVariableCatalog.MINIMUM).isNotNull();
    GlobalValidator.assertThat(max).thatIsNamed(LowerCaseVariableCatalog.MAXIMUM).isNotSmallerThan(min);
    GlobalValidator.assertThat(decimalPlaces).thatIsNamed("big decimal scale").isPositive();

    this.min = min.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.max = max.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ClosedInterval(final double min, final double max) {
    this(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
  }

  public ClosedInterval(final double min, final double max, final int decimalPlaces) {
    this(BigDecimal.valueOf(min), BigDecimal.valueOf(max), decimalPlaces);
  }

  @Override
  public boolean containsValue(final BigDecimal value) {
    return value != null
    && value.compareTo(min) >= 0
    && value.compareTo(max) <= 0;
  }

  @Override
  public boolean equals(final Object object) {
    return //
    object instanceof final IClosedInterval closedInterval
    && equalsClosedIntervall(closedInterval);
  }

  @Override
  public int getDecimalPlaces() {
    return min.scale();
  }

  @Override
  public Pair<IClosedInterval, IClosedInterval> getHalfs() {

    final var decimalPlaces = getDecimalPlaces();
    final var midPoint = getMidPoint();

    return new Pair<>(
      new ClosedInterval(min, midPoint, decimalPlaces),
      new ClosedInterval(midPoint, max, decimalPlaces));
  }

  @Override
  public BigDecimal getLength() {
    return max.subtract(min);
  }

  @Override
  public BigDecimal getMax() {
    return max;
  }

  @Override
  public BigDecimal getMidPoint() {
    return min.add(max).divide(BigDecimal.valueOf(2.0)).setScale(min.scale());
  }

  @Override
  public BigDecimal getMin() {
    return min;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public ClosedInterval inDecimalPlaces(final int decimalPlaces) {
    return new ClosedInterval(min, max, decimalPlaces);
  }

  @Override
  public boolean intersectsWith(final IClosedInterval closedInterval) {
    return getMin().compareTo(closedInterval.getMax()) < 0
    && getMax().compareTo(closedInterval.getMin()) > 0;
  }

  @Override
  public String toString() {
    return ("[" + min + ", " + max + "]");
  }

  //For a better performance, this implementation does not use all comfortable methods.
  private boolean equalsClosedIntervall(final IClosedInterval closedInterval) {
    return //
    closedInterval != null
    && min.equals(closedInterval.getMin())
    && max.equals(closedInterval.getMax());
  }
}
