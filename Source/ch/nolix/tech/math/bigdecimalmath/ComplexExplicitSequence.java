package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;

public final class ComplexExplicitSequence extends Sequence<IComplexNumber> {

  private final IntFunction<IComplexNumber> valueFunction;

  public ComplexExplicitSequence(final IntFunction<IComplexNumber> valueFunction) {

    GlobalValidator.assertThat(valueFunction).thatIsNamed("value function").isNotNull();

    this.valueFunction = valueFunction;
  }

  @Override
  protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
    return value.getSquaredMagnitude();
  }

  @Override
  protected IComplexNumber calculateValue(final int index) {
    return valueFunction.apply(index);
  }
}
