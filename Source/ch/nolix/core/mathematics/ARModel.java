/*
 * file:	ARModel.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	130
 */

//package declaration
package ch.nolix.core.mathematics;

//own imports
import ch.nolix.core.validator.Validator;

//class
public final class ARModel extends StatisticalModel {

	//attributes
	private final double[] pCoefficients;
	private final double constant;
	
	//constructor
	/**
	 * Creates new AR model with the given p-order and the given input values.
	 * 
	 * @param pOrder
	 * @param inputValues
	 * @throws Exception if:
	 * -the given p-order is negative
	 * -less input values are given than the given p-order+1
	 */
	public ARModel(final int pOrder, final double[] inputValues) {
		
		//Calls constructor of the base class.
		super(pOrder, inputValues);
				
		//Creates factor1 matrix.
		Matrix factor1Matrix = new Matrix(inputValues.length - pOrder, pOrder + 1);
		for (int i = 1; i <= factor1Matrix.getRowCount(); i++) {
			
			for (int j = 1; j < factor1Matrix.getColumnCount(); j++) {
				factor1Matrix.setValue(i, j, inputValues[i + factor1Matrix.getColumnCount() - j - 2]);
			}
			
			factor1Matrix.setValue(i, factor1Matrix.getColumnCount(), 1);
		}
		
		//Creates product matrix.
		Matrix productMatrix = new Matrix(inputValues.length - pOrder, 1);
		for (int i = pOrder; i < inputValues.length; i++) {
			productMatrix.setValue(i - pOrder + 1, 1, inputValues[i]);
		}
		
		//Calculates factor2 matrix.
		Matrix factor2Matrix = factor1Matrix.getMinimalFactorMatrix(productMatrix);
		
		//Sets p-coefficients.
		pCoefficients = new double[pOrder];
		for (int i = 0; i < pOrder; i++) {
			pCoefficients[i] = factor2Matrix.getValue(i + 1, 1);
		}
		
		//Sets constant.
		constant = factor2Matrix.getValue(factor2Matrix.getSize(), 1);
	}
	
	public final double getNextValue() {
		
		double nextValue = constant;
		for (int i = 0; i < getPOrder(); i++) {
			nextValue += pCoefficients[i] * getValueFromBack(i + 1);
		}
		
		return nextValue;
	}
	
	//method
	/**
	 * @return the constant of this AR model
	 */
	public final double getConstant() {
		return constant;
	}
	
	//method
	/**
	 * @param index
	 * @return the p-coefficient of this AR model with the given index
	 * @throws Exception if this AR model has no p-coefficient with the given index
	 */
	public final double getPCoefficient(final int index) {
	
		Validator.throwExceptionIfValueIsNotInRange("p-coefficient index", 1, getPOrder(), index);
		
		return pCoefficients[index - 1];
	}
	
	//method
	/**
	 * @return the p-order of this AR model
	 */
	public final int getPOrder() {
		return pCoefficients.length;
	}
	
	void initialize() {
		
	}
}
