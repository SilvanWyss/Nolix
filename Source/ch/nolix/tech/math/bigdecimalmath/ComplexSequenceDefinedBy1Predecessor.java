/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.bigdecimalmath.ISequenceDefinedBy1Predecessor;

/**
 * @author Silvan Wyss
 */
public final class ComplexSequenceDefinedBy1Predecessor
extends AbstractSequence<IComplexNumber>
implements ISequenceDefinedBy1Predecessor<IComplexNumber> {
  private final IComplexNumber firstValue;

  private final Function<IComplexNumber, IComplexNumber> nextValueFunction;

  private ComplexSequenceDefinedBy1Predecessor(
    final IComplexNumber firstValue,
    final UnaryOperator<IComplexNumber> nextValueFunction) {
    Validator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
    Validator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();

    this.firstValue = firstValue;
    this.nextValueFunction = nextValueFunction;
  }

  public static ComplexSequenceDefinedBy1Predecessor withFirstValueAndNextValueFunction(
    final IComplexNumber firstValue,
    final UnaryOperator<IComplexNumber> nextValueFunction) {
    return new ComplexSequenceDefinedBy1Predecessor(firstValue, nextValueFunction);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IComplexNumber getFirstValue() {
    return firstValue;
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
    if (index == 1) {
      return getFirstValue();
    }

    final var predecessor = getValueAtIndexWhenCalculated(index - 1);

    return nextValueFunction.apply(predecessor);
  }
}
