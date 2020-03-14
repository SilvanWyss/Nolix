//package declaration
package ch.nolix.commonTest.mathTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class MathematicsTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link MathematicsTestPool}.
	 */
	public MathematicsTestPool() {
		super(
			ARModelTest.class,
			CalculatorTest.class,
			MatrixTest.class,
			PolynomTest.class,
			QuadraticFunctionTest.class
		);
	}
}
