//package declaration
package ch.nolix.coreTest.containerTest;

//own imports
import ch.nolix.core.container.Matrix;
import ch.nolix.core.test2.Test;

//test class
/**
 * This class is a test class for the matrix class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class MatrixTest extends Test {

	//test method
	public final void testAddRow() {
		
		//setup
		final Matrix<String> matrix	= new Matrix<String>()
				
		//execution
		.addRow("apple", "banana", "cerish");
		
		//verification
		expectThat(matrix.getElementCount()).isEqualTo(3);
		expectThat(matrix.getRowCount()).isEqualTo(1);
		expectThat(matrix.getColumnCount()).isEqualTo(3);
		expectThat(matrix.toString()).equalsTo("apple,banana,cerish");
	}
}
