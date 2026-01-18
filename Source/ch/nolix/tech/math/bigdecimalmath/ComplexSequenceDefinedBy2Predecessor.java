package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.bigdecimalmath.ISequenceDefinedBy2Predecessor;

/**
 * @author Silvan Wyss
 */
public final class ComplexSequenceDefinedBy2Predecessor
extends AbstractSequence<IComplexNumber>
implements ISequenceDefinedBy2Predecessor<IComplexNumber> {
  private final IComplexNumber firstValue;

  private final IComplexNumber secondValue;

  private final BiFunction<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction;

  public ComplexSequenceDefinedBy2Predecessor(
    final IComplexNumber firstValue,
    final IComplexNumber secondValue,
    BinaryOperator<IComplexNumber> nextValueFunction) {
    Validator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
    Validator.assertThat(secondValue).thatIsNamed("second value").isNotNull();
    Validator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();

    this.firstValue = firstValue;
    this.secondValue = secondValue;
    this.nextValueFunction = nextValueFunction;
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

    return nextValueFunction.apply(getValueAtOneBasedIndex(index - 2), getValueAtOneBasedIndex(index - 1));
  }
}
