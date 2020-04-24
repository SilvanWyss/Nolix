//package declaration
package ch.nolix.common.math;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.validator.Validator;

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
		
		while (forecasts.getSize() < index) {
			forecasts.addAtEnd(calculateNextValue());
		}
		
		return forecasts.getRefAt(index);
	}
	
	//method declaration
	protected abstract double calculateNextValue();

	//method
	protected final int getIndexOfNextValue() {
		return (getInputValuesCount() + forecasts.getSize() + 1);
	}

	//method
	protected final double getValueFromBack(final int index) {
		
		Validator.assertThat(index).thatIsNamed(VariableNameCatalogue.INDEX).isPositive();
		
		Validator
		.assertThat(index)
		.thatIsNamed(VariableNameCatalogue.INDEX)
		.isNotBiggerThan(inputValues.length + forecasts.getSize());
		
		if (index > forecasts.getSize()) {
			return inputValues[getInputValuesCount() + forecasts.getSize() - index];
		}
		
		return forecasts.getRefAt(forecasts.getSize() - index + 1);
	}
}
