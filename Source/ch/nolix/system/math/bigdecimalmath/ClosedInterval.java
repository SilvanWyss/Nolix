/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.math.bigdecimalmath;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.nolix.base.datastructure.pair.Pair;
import ch.nolix.base.math.main.Calculator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.math.bigdecimalmath.IClosedInterval;

/**
 * A {@link ClosedInterval} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ClosedInterval implements IClosedInterval {
  private final BigDecimal min;

  private final BigDecimal max;

  /**
   * Creates a new {@link ClosedInterval} with the given min, max and their max
   * decimal place count.
   * 
   * @param min
   * @param max
   * @throws RuntimeException if the given min is null
   * @throws RuntimeException if the given max is null
   */
  private ClosedInterval(final BigDecimal min, final BigDecimal max) {
    Validator.assertThat(min).thatIsNamed(LowerCaseVariableNameCatalog.MINIMUM).isNotNull();
    Validator.assertThat(max).thatIsNamed(LowerCaseVariableNameCatalog.MAXIMUM).isNotSmallerThan(min);

    final var decimalPlaceCount = Calculator.getMax(min.scale(), max.scale());

    this.min = min.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
    this.max = max.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
  }

  /**
   * Creates a new {@link ClosedInterval} with the given min, max and
   * decimalPlaceCount.
   * 
   * @param min
   * @param max
   * @param decimalPlaceCount
   * @throws RuntimeException if the given min is null
   * @throws RuntimeException if the given max is null
   * @throws RuntimeException if the given decimalPlaceCount is not positive
   */
  private ClosedInterval(final BigDecimal min, final BigDecimal max, final int decimalPlaceCount) {
    Validator.assertThat(min).thatIsNamed(LowerCaseVariableNameCatalog.MINIMUM).isNotNull();
    Validator.assertThat(max).thatIsNamed(LowerCaseVariableNameCatalog.MAXIMUM).isNotSmallerThan(min);
    Validator.assertThat(decimalPlaceCount).thatIsNamed("decimal place count").isPositive();

    this.min = min.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
    this.max = max.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
  }

  /**
   * Creates a new {@link ClosedInterval} with the given min, max and their max
   * decimal place count.
   * 
   * @param min
   * @param max
   */
  private ClosedInterval(final double min, final double max) {
    this(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
  }

  /**
   * Creates a new {@link ClosedInterval} with the given min, max and
   * decimalPlaceCount.
   * 
   * @param min
   * @param max
   * @param decimalPlaceCount
   * @throws RuntimeException if the given decimalPlaceCount is not positive
   */
  private ClosedInterval(final double min, final double max, final int decimalPlaceCount) {
    this(BigDecimal.valueOf(min), BigDecimal.valueOf(max), decimalPlaceCount);
  }

  /**
   * @param min
   * @param max
   * @return a new {@link ClosedInterval} with the given min, max and their max
   *         decimal place count
   * @throws RuntimeException if the given min is null
   * @throws RuntimeException if the given max is null
   */
  public static ClosedInterval withMinAndMax(final BigDecimal min, final BigDecimal max) {
    return new ClosedInterval(min, max);
  }

  /**
   * @param min
   * @param max
   * @param decimalPlaceCount
   * @return a new {@link ClosedInterval} with the given min, max and
   *         decimalPlaceCount
   * @throws RuntimeException if the given min is null
   * @throws RuntimeException if the given max is null
   * @throws RuntimeException if the given decimalPlaceCount is not positive
   */
  public static ClosedInterval withMinAndMaxAndDecimalPlaceCount(
    final BigDecimal min,
    final BigDecimal max,
    int decimalPlaceCount) {
    return new ClosedInterval(min, max, decimalPlaceCount);
  }

  /**
   * @param min
   * @param max
   * @return a new {@link ClosedInterval} with the given min, max and their max*
   *         decimal place count.
   */
  public static ClosedInterval withMinAndMax(final double min, final double max) {
    return new ClosedInterval(min, max);
  }

  /**
   * @param min
   * @param max
   * @param decimalPlaceCount
   * @return a new {@link ClosedInterval} with the given min, max and
   *         decimalPlaceCount
   * @throws RuntimeException if the given decimalPlaceCount is not positive
   */
  public static ClosedInterval withMinAndMaxAndDecimalPlaceCount(
    final double min,
    final double max,
    final int decimalPlaceCount) {
    return new ClosedInterval(min, max, decimalPlaceCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsValue(final BigDecimal value) {
    return //
    value != null
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
    && min.equals(closedInterval.getMin())
    && max.equals(closedInterval.getMax());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDecimalPlaceCount() {
    return min.scale();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<IClosedInterval, IClosedInterval> getHalfs() {
    final var decimalPlaceCount = getDecimalPlaceCount();
    final var midPoint = getMidPoint();
    final var leftHalf = new ClosedInterval(min, midPoint, decimalPlaceCount);
    final var rightHalf = new ClosedInterval(midPoint, max, decimalPlaceCount);

    return Pair.withElement1AndElement2(leftHalf, rightHalf);
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
    return min.add(max).divide(BigDecimal.valueOf(2.0)).setScale(getDecimalPlaceCount());
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
  public boolean intersectsWithClosedInterval(final IClosedInterval closedInterval) {
    return //
    min.compareTo(closedInterval.getMax()) < 0
    && max.compareTo(closedInterval.getMin()) > 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ("[" + min + ", " + max + "]");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ClosedInterval withDecimalPlaceCount(final int decimalPlaceCount) {
    return withMinAndMaxAndDecimalPlaceCount(min, max, decimalPlaceCount);
  }
}
