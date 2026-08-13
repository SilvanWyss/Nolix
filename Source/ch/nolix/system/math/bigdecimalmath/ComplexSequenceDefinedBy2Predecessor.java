/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.systemapi.math.bigdecimalmath.ISequenceDefinedBy2Predecessor;

/**
 * @author Silvan Wyss
 */
public final class ComplexSequenceDefinedBy2Predecessor
extends AbstractSequence<IComplexNumber>
implements ISequenceDefinedBy2Predecessor<IComplexNumber> {
  private final IComplexNumber firstValue;

  private final IComplexNumber secondValue;

  private final BiFunction<IComplexNumber, IComplexNumber, IComplexNumber> nextValueSupplier;

  private ComplexSequenceDefinedBy2Predecessor(
    final IComplexNumber firstValue,
    final IComplexNumber secondValue,
    BinaryOperator<IComplexNumber> nextValueSupplier) {
    Validator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
    Validator.assertThat(secondValue).thatIsNamed("second value").isNotNull();
    Validator.assertThat(nextValueSupplier).thatIsNamed("next value supplier").isNotNull();

    this.firstValue = firstValue;
    this.secondValue = secondValue;
    this.nextValueSupplier = nextValueSupplier;
  }

  public static ComplexSequenceDefinedBy2Predecessor withFirstValueAndSecondValueAndNextValueSupplier(
    final IComplexNumber firstValue,
    final IComplexNumber secondValue,
    final BinaryOperator<IComplexNumber> nextValueSupplier) {
    return new ComplexSequenceDefinedBy2Predecessor(firstValue, secondValue, nextValueSupplier);
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
  public IComplexNumber getSecondValue() {
    return secondValue;
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

    if (index == 2) {
      return getSecondValue();
    }

    return nextValueSupplier.apply(getValueAtOneBasedIndex(index - 2), getValueAtOneBasedIndex(index - 1));
  }
}
