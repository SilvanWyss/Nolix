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
    return new IntRoundingWithModeMediator(value, RoundingMode.DOWN).toNext(step);
  }

  //method
  public int toNext(final int step) {
    return withMode(RoundingMode.UP_WHEN_REST_IS_HALF_STEP).toNext(step);
  }

  //method
  public int upToNext(final int step) {
    return new IntRoundingWithModeMediator(value, RoundingMode.UP).toNext(step);
  }

  //method
  public IntRoundingWithModeMediator withMode(final RoundingMode roundingMode) {
    return new IntRoundingWithModeMediator(value, roundingMode);
  }
}
