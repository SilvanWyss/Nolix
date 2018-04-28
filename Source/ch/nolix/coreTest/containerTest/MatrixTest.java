//package declaration
package ch.nolix.coreTest.containerTest;

//own imports
import ch.nolix.core.container.Matrix;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A {@link MatrixTest} is a {@link Test} for the {@link Matrix} class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 60
 */
public final class MatrixTest extends Test {

	//test method
	public void test_addRow() {
		
		//setup
		final var matrix = new Matrix<String>();
				
		//execution
		matrix.addRow("apple", "banana", "cerish");
		
		//verification
		expect(matrix.getElementCount()).isEqualTo(3);
		expect(matrix.getRowCount()).isEqualTo(1);
		expect(matrix.getColumnCount()).isEqualTo(3);
		expect(matrix.toString()).isEqualTo("apple,banana,cerish");
	}
	
	//test method
	public void test_getColumn() {
		
		//setup
			final var matrix = new Matrix<String>();
		
			matrix.addRow("apple", "banana", "cerish");
			matrix.addRow("elephant", "lion", "monkey");
			matrix.addRow("flower", "tree", "palm");
			
		//execution
		final var column = matrix.getColumn(3);
		
		//verification
		expect(column.getElementCount()).isEqualTo(3);
		expect(column.toString()).isEqualTo("cerish,monkey,palm");
	}
	
	//test method
	public void test_getRow() {
		
		//setup
			final var matrix = new Matrix<String>();
		
			matrix.addRow("apple", "banana", "cerish");
			matrix.addRow("elephant", "lion", "monkey");
			matrix.addRow("flower", "tree", "palm");
			
		//execution
		final var column = matrix.getRow(3);
		
		//verification
		expect(column.getElementCount()).isEqualTo(3);
		expect(column.toString()).isEqualTo("flower,tree,palm");
	}
}
