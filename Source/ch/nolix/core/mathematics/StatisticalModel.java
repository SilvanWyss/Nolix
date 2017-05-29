/*
 * file:	StatisticalModel.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.core.mathematics;

import ch.nolix.core.container.List;
import ch.nolix.core.validator.Validator;

//class
public abstract class StatisticalModel {

	//attributes
	private final int backStepsCount;
	private final double[] inputValues;
	private double[] calculatedValues;
	private final List<Double>forecasts = new List<Double>();
	
	//constructor
	public StatisticalModel(final int backStepsCount, final double[] inputValues) {
		
		//Checks the givne parameters.
		Validator.throwExceptionIfValueIsZero("back steps count", backStepsCount);
		//TODO check input values min length
		
		//Sets the back steps count of this statistical model.
		this.backStepsCount = backStepsCount;
		
		//Sets the input values of this statistical model.
		this.inputValues = inputValues.clone();	
	}
	
	//method
	/**
	 * @return the number of back steps of this statistical model
	 */
	public final int getBackStepsCount() {
		return backStepsCount;
	}
	
	//abstract method
	public abstract double getNextValue();
	
	//method
	public final double getValueFromBack(final int index) {
		
		Validator.throwExceptionIfValueIsNotPositive("index", index);
		Validator.throwExceptionIfValueIsBigger("index", inputValues.length + forecasts.getSize(), index);
		
		if (index > forecasts.getSize()) {
			return inputValues[getInputValuesCount() + forecasts.getSize() - index];
		}
		
		return forecasts.getRefAt(forecasts.getSize() - index + 1);
	}
	
	//method
	public final double getForecast(final int index) {
		
		Validator.throwExceptionIfValueIsNotPositive("index", index);
		
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
	
	void calculateValues() {
		
		//Sets the calculated values of this statistical model.
		calculatedValues = new double[getInputValuesCount()];
		for (int i = 0; i < getBackStepsCount(); i++) {
			calculatedValues[i] = inputValues[i];
		}
		for (int i = getBackStepsCount(); i < getInputValuesCount(); i++) {
			calculatedValues[i] = getNextValue();
		}
	}
}
