package ch.nolix.commonTest.mathematicsTest;

//Java import
import java.util.Vector;







import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.zetaTest.ZetaTest;

public class CalculatorTest extends ZetaTest {

	public void testAverageWithVector() {
		
		//setup
		Vector<Double> vector = new Vector<Double>();
		vector.add(1.0);
		vector.add(2.0);
		vector.add(3.0);
		vector.add(4.0);
		
		//execution and verification
		expectThat(Calculator.DOUBLE_AVERAGE.getOutput(vector)).equals(2.5);
	}
}
