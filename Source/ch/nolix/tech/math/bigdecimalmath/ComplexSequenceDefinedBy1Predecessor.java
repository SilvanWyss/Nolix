package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequenceDefinedBy1Predecessor;

public final class ComplexSequenceDefinedBy1Predecessor
extends Sequence<IComplexNumber>
implements ISequenceDefinedBy1Predecessor<IComplexNumber> {

  private final IComplexNumber firstValue;

  private final Function<IComplexNumber, IComplexNumber> nextValueFunction;

  public ComplexSequenceDefinedBy1Predecessor(
    final IComplexNumber firstValue,
    final UnaryOperator<IComplexNumber> nextValueFunction) {

    GlobalValidator.assertThat(firstValue).thatIsNamed("first value").isNotNull();
    GlobalValidator.assertThat(nextValueFunction).thatIsNamed("next value function").isNotNull();

    this.firstValue = firstValue;
    this.nextValueFunction = nextValueFunction;
  }

  @Override
  public IComplexNumber getFirstValue() {
    return firstValue;
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

    final var predecessor = getValueAtIndexWhenCalculated(index - 1);

    return nextValueFunction.apply(predecessor);
  }
}
