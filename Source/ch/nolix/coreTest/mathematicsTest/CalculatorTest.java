//package declaration
package ch.nolix.coreTest.mathematicsTest;

//Java import
import java.util.Vector;

//own imports


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

	//test case
	public void testCase_doubleAverage() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution & verification
		expect(Calculator.DOUBLE_AVERAGE.getOutput(vector)).isEqualTo(2.5);
	}
	
	//test case
	public void testCase_doubleMax() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution & verification
		expect(Calculator.DOUBLE_MAX.getOutput(vector)).isEqualTo(4.0);
	}
	
	//test case
	public void testCase_doubleMin() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution & verification
		expect(Calculator.DOUBLE_MIN.getOutput(vector)).isEqualTo(1.0);
	}
	
	//test case
	public void testCase_doubleSum() {
		
		//setup
		final Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution & verification
		expect(Calculator.DOUBLE_SUM.getOutput(vector)).isEqualTo(10.0);
	}
}
