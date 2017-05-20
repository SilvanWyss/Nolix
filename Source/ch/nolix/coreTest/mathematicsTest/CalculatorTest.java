//package declaration
package ch.nolix.coreTest.mathematicsTest;

//Java import
import java.util.Vector;

//own imports


import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.test2.Test;

/**
 * This class is a test class for the calculator class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 70
 */
public final class CalculatorTest extends Test {

	//test method
	public void test_doubleAverage() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution and verification
		expectThat(Calculator.DOUBLE_AVERAGE.getOutput(vector)).equals(2.5);
	}
	
	//test method
	public void test_doubleMax() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution and verification
		expectThat(Calculator.DOUBLE_MAX.getOutput(vector)).equals(4.0);
	}
	
	//test method
	public void test_doubleMin() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution and verification
		expectThat(Calculator.DOUBLE_MIN.getOutput(vector)).equals(1.0);
	}
	
	//test method
	public void test_doubleSum() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution and verification
		expectThat(Calculator.DOUBLE_SUM.getOutput(vector)).equals(10.0);
	}
}
