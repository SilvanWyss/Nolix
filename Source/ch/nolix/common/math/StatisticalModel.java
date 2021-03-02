//package declaration
package ch.nolix.common.math;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 80
 */
public abstract class StatisticalModel {
	
	//attributes
	private final int backStepCount;
	
	//multi-attributes
	private final double[] inputValues;
	private final LinkedList<Double>forecasts = new LinkedList<>();
	
	//constructor
	public StatisticalModel(final int backStepsCount, final double[] inputValues) {
				
		Validator.assertThat(inputValues).thatIsNamed("input values").isNotEmpty();
		Validator.assertThat(backStepsCount).thatIsNamed("back step count").isBetween(1, inputValues.length);
		
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
		
		Validator.assertThat(index).thatIsNamed("index").isPositive();
		
		while (forecasts.getElementCount() < index) {
			forecasts.addAtEnd(calculateNextValue());
		}
		
		return forecasts.getRefAt(index);
	}
	
	//method declaration
	protected abstract double calculateNextValue();

	//method
	protected final int getIndexOfNextValue() {
		return (getInputValuesCount() + forecasts.getElementCount() + 1);
	}

	//method
	protected final double getValueFromBack(final int index) {
		
		Validator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		
		Validator
		.assertThat(index)
		.thatIsNamed(LowerCaseCatalogue.INDEX)
		.isNotBiggerThan(inputValues.length + forecasts.getElementCount());
		
		if (index > forecasts.getElementCount()) {
			return inputValues[getInputValuesCount() + forecasts.getElementCount() - index];
		}
		
		return forecasts.getRefAt(forecasts.getElementCount() - index + 1);
	}
}
