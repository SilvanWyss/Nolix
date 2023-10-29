//package declaration
package ch.nolix.core.math.stochastic;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public abstract class StatisticalModel {

  //attribute
  private final int backStepCount;

  //multi-attribute
  private final double[] inputValues;

  //multi-attribute
  private final LinkedList<Double> forecasts = new LinkedList<>();

  //constructor
  protected StatisticalModel(final int backStepsCount, final double[] inputValues) {

    GlobalValidator.assertThat(inputValues).thatIsNamed("input values").isNotEmpty();
    GlobalValidator.assertThat(backStepsCount).thatIsNamed("back step count").isBetween(1, inputValues.length);

    this.backStepCount = backStepsCount;
    this.inputValues = inputValues.clone();
  }

  //method
  public final int getBackStepsCount() {
    return backStepCount;
  }

  //method
  public final int getInputValuesCount() {
    return inputValues.length;
  }

  //method
  public final double getForecast(final int index) {

    GlobalValidator.assertThat(index).thatIsNamed("index").isPositive();

    while (forecasts.getElementCount() < index) {
      forecasts.addAtEnd(calculateNextValue());
    }

    return forecasts.getStoredAt1BasedIndex(index);
  }

  //method declaration
  protected abstract double calculateNextValue();

  //method
  protected final int getIndexOfNextValue() {
    return (getInputValuesCount() + forecasts.getElementCount() + 1);
  }

  //method
  protected final double getValueFromBack(final int index) {

    GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();

    GlobalValidator
      .assertThat(index)
      .thatIsNamed(LowerCaseCatalogue.INDEX)
      .isNotBiggerThan(inputValues.length + forecasts.getElementCount());

    if (index > forecasts.getElementCount()) {
      return inputValues[getInputValuesCount() + forecasts.getElementCount() - index];
    }

    return forecasts.getStoredAt1BasedIndex(forecasts.getElementCount() - index + 1);
  }
}
