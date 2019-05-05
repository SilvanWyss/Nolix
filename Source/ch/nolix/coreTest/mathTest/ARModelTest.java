//package declaration
package ch.nolix.coreTest.mathTest;

import ch.nolix.core.math.ARModel;
import ch.nolix.core.test.Test;

//class
/**
 * This class is a test class for the AR model class
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 100
 */
public final class ARModelTest extends Test {

	//test case
	public void testCase_creation_1() {
		
		//execution
		final double[] inputValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		final ARModel ARModel_ = new ARModel(1, inputValues);
		
		//verification
		expect(ARModel_.getForecast(1)).withMaxDeviation(0.01).isEqualTo(11.0);
		expect(ARModel_.getForecast(2)).withMaxDeviation(0.01).isEqualTo(12.0);
		expect(ARModel_.getForecast(3)).withMaxDeviation(0.01).isEqualTo(13.0);
		expect(ARModel_.getForecast(4)).withMaxDeviation(0.01).isEqualTo(14.0);
	}
	
	//test case
	public void testCase_creation_2() {
		
		//execution
		final double[] inputValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		final ARModel ARModel_ = new ARModel(2, inputValues);
		
		//verification
		expect(ARModel_.getForecast(1)).withMaxDeviation(0.01).isEqualTo(11.0);
		expect(ARModel_.getForecast(2)).withMaxDeviation(0.01).isEqualTo(12.0);
		expect(ARModel_.getForecast(3)).withMaxDeviation(0.01).isEqualTo(13.0);
		expect(ARModel_.getForecast(4)).withMaxDeviation(0.01).isEqualTo(14.0);
	}
	
	//test case
	public void testCase_creation_3() {
		
		//execution
		final double[] inputValues = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
		final ARModel ARModel_ = new ARModel(2, inputValues);

		//verification
		expect(ARModel_.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
		expect(ARModel_.getForecast(3)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(4)).withMaxDeviation(0.01).isEqualTo(2.0);
	}
	
	//test case
	public void testCase_creation_4() {
		
		//execution
		final double[] inputValues = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
		final ARModel ARModel_ = new ARModel(3, inputValues);
	
		//verification
		expect(ARModel_.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
		expect(ARModel_.getForecast(3)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(4)).withMaxDeviation(0.01).isEqualTo(2.0);
	}
	
	//test case
	public void testCase_creation_5() {
		
		//execution
		final double[] inputValues = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3};
		final ARModel ARModel_ = new ARModel(3, inputValues);
		
		//verification
		expect(ARModel_.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
		expect(ARModel_.getForecast(3)).withMaxDeviation(0.01).isEqualTo(3.0);
		expect(ARModel_.getForecast(4)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(5)).withMaxDeviation(0.01).isEqualTo(2.0);
		expect(ARModel_.getForecast(6)).withMaxDeviation(0.01).isEqualTo(3.0);
	}
	
	//test case
	public final void testCase_creation_6() {
		
		//exection
		final double[] inputValues = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3};
		final ARModel ARModel_ = new ARModel(4, inputValues);
		
		//verification
		expect(ARModel_.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
		expect(ARModel_.getForecast(3)).withMaxDeviation(0.01).isEqualTo(3.0);
		expect(ARModel_.getForecast(4)).withMaxDeviation(0.01).isEqualTo(1.0);
		expect(ARModel_.getForecast(5)).withMaxDeviation(0.01).isEqualTo(2.0);
		expect(ARModel_.getForecast(6)).withMaxDeviation(0.01).isEqualTo(3.0);
	}
}
