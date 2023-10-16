//package declaration
package ch.nolix.tech.math.bigdecimalmath;

//Java imports
import java.math.BigDecimal;
import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;

//class
public final class ComplexExplicitSequence extends Sequence<IComplexNumber> {

  //attribute
  private final IntFunction<IComplexNumber> valueFunction;

  //constructor
  public ComplexExplicitSequence(final IntFunction<IComplexNumber> valueFunction) {

    GlobalValidator.assertThat(valueFunction).thatIsNamed("value function").isNotNull();

    this.valueFunction = valueFunction;
  }

  //method
  @Override
  protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
    return value.getSquaredMagnitude();
  }

  //method
  @Override
  protected IComplexNumber calculateValue(final int index) {
    return valueFunction.apply(index);
  }
}
