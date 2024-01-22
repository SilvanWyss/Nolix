//package declaration
package ch.nolix.core.math.basic;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.mathapi.basicapi.RoundingMode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class IntRoundingWithModeMediator {

  //attribute
  private final int value;

  //attribute
  private final RoundingMode roundingMode;

  //class
  IntRoundingWithModeMediator(final int value, final RoundingMode roundingMode) {

    GlobalValidator
      .assertThat(roundingMode)
      .thatIsNamed("rounding mode")
      .isNotNull();

    this.value = value;
    this.roundingMode = roundingMode;
  }

  //method
  public int toNext(final int step) {

    GlobalValidator.assertThat(step).thatIsNamed(LowerCaseVariableCatalogue.STEP).isPositive();

    final var rest = value % step;

    if (rest == 0) {
      return value;
    }

    switch (roundingMode) {
      case DOWN_WHEN_DELTA_IS_SMALLER_THAN_HALF_STEP:
        return (value - rest);
      case DOWN_WHEN_DELTA_IS_UP_TO_HALF_STEP:

        if (rest <= step / 2) {
          return (value - rest);
        }

        return (value - rest + step);
      case UP_WHEN_DELTA_IS_SMALLER_THAN_HALF_STEP:
        return (value - rest + step);
      default:

        if (rest < step / 2) {
          return (value - rest);
        }

        return (value - rest + step);
    }
  }
}
