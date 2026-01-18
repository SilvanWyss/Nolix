/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;

/**
 * @author Silvan Wyss
 */
public final class ComplexExplicitSequence extends AbstractSequence<IComplexNumber> {
  private final IntFunction<IComplexNumber> valueFunction;

  public ComplexExplicitSequence(final IntFunction<IComplexNumber> valueFunction) {
    Validator.assertThat(valueFunction).thatIsNamed("value function").isNotNull();

    this.valueFunction = valueFunction;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
    return value.getSquaredMagnitude();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IComplexNumber calculateValue(final int index) {
    return valueFunction.apply(index);
  }
}
