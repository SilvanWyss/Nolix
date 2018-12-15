//package declaration
package ch.nolix.core.mathematics;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 10
 */
public abstract class StatisticalModel {

	//attributes
	private final int backStepCount;
	private final double[] inputValues;
	private double[] calculatedInputValues;
	private final List<Double>forecasts = new List<Double>();
	
	//constructor
	/**
	 * Creates a new statistical model with the given back step count and input values.
	 * 
	 * @param backStepsCount
	 * @param inputValues
	 */
	public StatisticalModel( final int backStepsCount, final double[] inputValues) {
		
		Validator.suppose(inputValues).thatIsNamed("input values").isNotEmpty();
		Validator.suppose(backStepsCount).thatIsNamed("back step count").isBetween(1, inputValues.length);
		
		//Sets the back steps count of this statistical model.
		this.backStepCount = backStepsCount;
		
		//Sets the input values of this statistical model.
		this.inputValues = inputValues.clone();
	}
	
	//method
	/**
	 * @return the number of back steps of this statistical model
	 */
	public final int getBackStepsCount() {
		return backStepCount;
	}
	
	//abstract method
	public abstract double getNextValue();
	
	//method
	public final double getValueFromBack(final int index) {
		
		Validator.suppose(index).thatIsNamed("index").isPositive();
		Validator.suppose(index).thatIsNamed("index").isNotBiggerThan(inputValues.length + forecasts.getSize());
		
		if (index > forecasts.getSize()) {
			return inputValues[getInputValuesCount() + forecasts.getSize() - index];
		}
		
		return forecasts.getRefAt(forecasts.getSize() - index + 1);
	}
	
	//method
	public final double getForecast(final int index) {
		
		Validator.suppose(index).thatIsNamed("index").isPositive();
		
		while (forecasts.getSize() < index) {
			forecasts.addAtEnd(getNextValue());
		}
		
		return forecasts.getRefAt(index);
	}
	
	//method
	public final int getIndexOfNextValue() {
		return (getInputValuesCount() + forecasts.getSize() + 1);
	}
	
	//method
	public final int getInputValuesCount() {
		return inputValues.length;
	}
	
	void calculateInputValues() {
		
		//Sets the calculated values of this statistical model.
		calculatedInputValues = new double[getInputValuesCount()];
		for (int i = 0; i < getBackStepsCount(); i++) {
			calculatedInputValues[i] = inputValues[i];
		}
		for (int i = getBackStepsCount(); i < getInputValuesCount(); i++) {
			calculatedInputValues[i] = getNextValue();
		}
	}
}
