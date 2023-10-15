//package declaration
package ch.nolix.core.math.base;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class IntRoundingWithModeMediator {

  // attribute
  private final int value;

  // attribute
  private final RoundingMode roundingMode;

  // class
  IntRoundingWithModeMediator(final int value, final RoundingMode roundingMode) {

    GlobalValidator
        .assertThat(roundingMode)
        .thatIsNamed("rounding mode")
        .isNotNull();

    this.value = value;
    this.roundingMode = roundingMode;
  }

  // method
  public int toNext(final int step) {

    GlobalValidator.assertThat(step).thatIsNamed(LowerCaseCatalogue.STEP).isPositive();

    final var rest = value % step;

    if (rest == 0) {
      return value;
    }

    switch (roundingMode) {
      case DOWN:
        return (value - rest);
      case DOWN_WHEN_REST_IS_HALF_STEP:

        if (rest <= step / 2) {
          return (value - rest);
        }

        return (value - rest + step);
      case UP:
        return (value - rest + step);
      default:

        if (rest < step / 2) {
          return (value - rest);
        }

        return (value - rest + step);
    }
  }
}
