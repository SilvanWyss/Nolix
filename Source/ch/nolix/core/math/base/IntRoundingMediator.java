//package declaration
package ch.nolix.core.math.base;

//class
public final class IntRoundingMediator {

  //attribute
  private final int value;

  //constructor
  IntRoundingMediator(final int value) {
    this.value = value;
  }

  //method
  public int downToNext(final int step) {
    return new IntRoundingWithModeMediator(value, RoundingMode.DOWN_WHEN_DELTA_IS_SMALLER_THAN_HALF_STEP).toNext(step);
  }

  //method
  public int toNext(final int step) {
    return withMode(RoundingMode.UP_WHEN_DELTA_IS_UP_TO_HALF_STEP).toNext(step);
  }

  //method
  public int upToNext(final int step) {
    return new IntRoundingWithModeMediator(value, RoundingMode.UP_WHEN_DELTA_IS_SMALLER_THAN_HALF_STEP).toNext(step);
  }

  //method
  public IntRoundingWithModeMediator withMode(final RoundingMode roundingMode) {
    return new IntRoundingWithModeMediator(value, roundingMode);
  }
}
