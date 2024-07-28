//package declaration
package ch.nolix.core.math.stochastic;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * @author Silvan Wyss
 * @version 2016-09-01
 */
public abstract class StatisticalModel {

  //attribute
  private final int backStepCount;

  //multi-attribute
  private final double[] inputValues;

  //multi-attribute
  private final LinkedList<Double> forecasts = LinkedList.createEmpty();

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

    while (forecasts.getCount() < index) {
      forecasts.addAtEnd(calculateNextValue());
    }

    return forecasts.getStoredAt1BasedIndex(index);
  }

  //method declaration
  protected abstract double calculateNextValue();

  //method
  protected final int getIndexOfNextValue() {
    return (getInputValuesCount() + forecasts.getCount() + 1);
  }

  //method
  protected final double getValueFromBack(final int index) {

    GlobalValidator.assertThat(index).thatIsNamed(LowerCaseVariableCatalogue.INDEX).isPositive();

    GlobalValidator
      .assertThat(index)
      .thatIsNamed(LowerCaseVariableCatalogue.INDEX)
      .isNotBiggerThan(inputValues.length + forecasts.getCount());

    if (index > forecasts.getCount()) {
      return inputValues[getInputValuesCount() + forecasts.getCount() - index];
    }

    return forecasts.getStoredAt1BasedIndex(forecasts.getCount() - index + 1);
  }
}
