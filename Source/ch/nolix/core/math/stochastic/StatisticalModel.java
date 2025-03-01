package ch.nolix.core.math.stochastic;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2016-09-01
 */
public abstract class StatisticalModel {

  private final int backStepCount;

  private final double[] inputValues;

  private final LinkedList<Double> forecasts = LinkedList.createEmpty();

  protected StatisticalModel(final int backStepsCount, final double[] inputValues) {

    Validator.assertThat(inputValues).thatIsNamed("input values").isNotEmpty();
    Validator.assertThat(backStepsCount).thatIsNamed("back step count").isBetween(1, inputValues.length);

    this.backStepCount = backStepsCount;
    this.inputValues = inputValues.clone();
  }

  public final int getBackStepsCount() {
    return backStepCount;
  }

  public final int getInputValuesCount() {
    return inputValues.length;
  }

  public final double getForecast(final int index) {

    Validator.assertThat(index).thatIsNamed("index").isPositive();

    while (forecasts.getCount() < index) {
      forecasts.addAtEnd(calculateNextValue());
    }

    return forecasts.getStoredAt1BasedIndex(index);
  }

  protected abstract double calculateNextValue();

  protected final int getIndexOfNextValue() {
    return (getInputValuesCount() + forecasts.getCount() + 1);
  }

  protected final double getValueFromBack(final int index) {

    Validator.assertThat(index).thatIsNamed(LowerCaseVariableCatalog.INDEX).isPositive();

    Validator
      .assertThat(index)
      .thatIsNamed(LowerCaseVariableCatalog.INDEX)
      .isNotBiggerThan(inputValues.length + forecasts.getCount());

    if (index > forecasts.getCount()) {
      return inputValues[getInputValuesCount() + forecasts.getCount() - index];
    }

    return forecasts.getStoredAt1BasedIndex(forecasts.getCount() - index + 1);
  }
}
