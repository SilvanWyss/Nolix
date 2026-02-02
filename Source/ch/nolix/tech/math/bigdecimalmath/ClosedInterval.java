/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.nolix.core.datastructure.pair.Pair;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.math.main.Calculator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.techapi.math.bigdecimalmath.IClosedInterval;

/**
 * @author Silvan Wyss
 */
public final class ClosedInterval implements IClosedInterval {
  private final BigDecimal min;

  private final BigDecimal max;

  public ClosedInterval(final BigDecimal min, final BigDecimal max) {
    Validator.assertThat(min).thatIsNamed(LowerCaseVariableCatalog.MINIMUM).isNotNull();
    Validator.assertThat(max).thatIsNamed(LowerCaseVariableCatalog.MAXIMUM).isNotSmallerThan(min);

    final var decimalPlaces = Calculator.getMax(min.scale(), max.scale());
    this.min = min.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.max = max.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ClosedInterval(final BigDecimal min, final BigDecimal max, final int decimalPlaces) {
    Validator.assertThat(min).thatIsNamed(LowerCaseVariableCatalog.MINIMUM).isNotNull();
    Validator.assertThat(max).thatIsNamed(LowerCaseVariableCatalog.MAXIMUM).isNotSmallerThan(min);
    Validator.assertThat(decimalPlaces).thatIsNamed("big decimal scale").isPositive();

    this.min = min.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.max = max.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ClosedInterval(final double min, final double max) {
    this(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
  }

  public ClosedInterval(final double min, final double max, final int decimalPlaces) {
    this(BigDecimal.valueOf(min), BigDecimal.valueOf(max), decimalPlaces);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsValue(final BigDecimal value) {
    return value != null
    && value.compareTo(min) >= 0
    && value.compareTo(max) <= 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    return //
    object instanceof final IClosedInterval closedInterval
    && equalsClosedIntervall(closedInterval);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDecimalPlaces() {
    return min.scale();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<IClosedInterval, IClosedInterval> getHalfs() {
    final var decimalPlaces = getDecimalPlaces();
    final var midPoint = getMidPoint();

    return //
    Pair.withElement1AndElement2(
      new ClosedInterval(min, midPoint, decimalPlaces),
      new ClosedInterval(midPoint, max, decimalPlaces));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getLength() {
    return max.subtract(min);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getMax() {
    return max;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getMidPoint() {
    return min.add(max).divide(BigDecimal.valueOf(2.0)).setScale(min.scale());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getMin() {
    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ClosedInterval inDecimalPlaces(final int decimalPlaces) {
    return new ClosedInterval(min, max, decimalPlaces);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean intersectsWith(final IClosedInterval closedInterval) {
    return getMin().compareTo(closedInterval.getMax()) < 0
    && getMax().compareTo(closedInterval.getMin()) > 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ("[" + min + ", " + max + "]");
  }

  //For a better performance, this implementation does not use all available comfort methods.
  private boolean equalsClosedIntervall(final IClosedInterval closedInterval) {
    return //
    closedInterval != null
    && min.equals(closedInterval.getMin())
    && max.equals(closedInterval.getMax());
  }
}
