package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequenceDefinedBy2Predecessor;

public final class ComplexSequenceDefinedBy2Predecessor
extends Sequence<IComplexNumber>
implements ISequenceDefinedBy2Predecessor<IComplexNumber> {

  private final IComplexNumber firstValue;

  private final IComplexNumber secondValue;

  private final BiFunction<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction;

  public ComplexSequenceDefinedBy2Predecessor(
    final IComplexNumber firstValue,
    final IComplexNumber secondValue,
    BinaryOperator<IComplexNumber> nextValueFunction) {

    GlobalValidator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
    GlobalValidator.assertThat(secondValue).thatIsNamed("second value").isNotNull();
    GlobalValidator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();

    this.firstValue = firstValue;
    this.secondValue = secondValue;
    this.nextValueFunction = nextValueFunction;
  }

  @Override
  public IComplexNumber getFirstValue() {
    return firstValue;
  }

  @Override
  public IComplexNumber getSecondValue() {
    return secondValue;
  }

  @Override
  protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
    return value.getSquaredMagnitude();
  }

  @Override
  protected IComplexNumber calculateValue(final int index) {

    if (index == 1) {
      return getFirstValue();
    }

    if (index == 2) {
      return getSecondValue();
    }

    return nextValueFunction.apply(getValueAt1BasedIndex(index - 2), getValueAt1BasedIndex(index - 1));
  }
}
