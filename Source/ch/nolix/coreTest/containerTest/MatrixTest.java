//package declaration
package ch.nolix.coreTest.containerTest;

//own imports
import ch.nolix.core.container.Matrix;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A {@link MatrixTest} is a test for the {@link Matrix} class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 140
 */
public final class MatrixTest extends Test {

	//test case
	public void testCase_addRow() {
		
		//setup
		final var matrix = new Matrix<String>();
				
		//execution
		matrix.addRow("apple", "banana", "cerish");
		
		//verification
		expect(matrix.getElementCount()).isEqualTo(3);
		expect(matrix.getRowCount()).isEqualTo(1);
		expect(matrix.getColumnCount()).isEqualTo(3);
		expect(matrix.getRow(1).toString()).isEqualTo("apple,banana,cerish");
	}
	
	//test case
	public void testCase_getColumn() {
		
		//setup
			final var matrix = new Matrix<String>();
		
			matrix.addRow("apple", "banana", "cerish");
			matrix.addRow("elephant", "lion", "monkey");
			matrix.addRow("flower", "tree", "palm");
			
		//execution
		final var column1 = matrix.getColumn(1);
		final var column2 = matrix.getColumn(2);
		final var column3 = matrix.getColumn(3);
		
		//verification
			expect(column1.getElementCount()).isEqualTo(3);
			expect(column1.toString()).isEqualTo("apple,elephant,flower");
			
			expect(column2.getElementCount()).isEqualTo(3);
			expect(column2.toString()).isEqualTo("banana,lion,tree");
			
			expect(column3.getElementCount()).isEqualTo(3);
			expect(column3.toString()).isEqualTo("cerish,monkey,palm");
	}
	
	//test case
	public void testCase_getIndexOf() {
		
		//setup
			final var matrix = new Matrix<String>();
			
			matrix.addRow("apple", "banana", "cerish");
			matrix.addRow("elephant", "lion", "monkey");
			matrix.addRow("flower", "tree", "palm");
			
		//execution
		final var index1 = matrix.getIndexOf(1, 1);
		final var index2 = matrix.getIndexOf(1, 2);
		final var index3 = matrix.getIndexOf(1, 3);
		final var index4 = matrix.getIndexOf(2, 1);
		final var index5 = matrix.getIndexOf(2, 2);
		final var index6 = matrix.getIndexOf(2, 3);
		final var index7 = matrix.getIndexOf(3, 1);
		final var index8 = matrix.getIndexOf(3, 2);
		final var index9 = matrix.getIndexOf(3, 3);
		
		//verification
		expect(index1).isEqualTo(1);
		expect(index2).isEqualTo(2);
		expect(index3).isEqualTo(3);
		expect(index4).isEqualTo(4);
		expect(index5).isEqualTo(5);
		expect(index6).isEqualTo(6);
		expect(index7).isEqualTo(7);
		expect(index8).isEqualTo(8);
		expect(index9).isEqualTo(9);
	}
	
	//test case
	public void testCase_getRow() {
		
		//setup
			final var matrix = new Matrix<String>();
		
			matrix.addRow("apple", "banana", "cerish");
			matrix.addRow("elephant", "lion", "monkey");
			matrix.addRow("flower", "tree", "palm");
			
		//execution
		final var row1 = matrix.getRow(1);
		final var row2 = matrix.getRow(2);
		final var row3 = matrix.getRow(3);
		
		//verification
			expect(row1.getElementCount()).isEqualTo(3);
			expect(row1.toString()).isEqualTo("apple,banana,cerish");
			
			expect(row2.getElementCount()).isEqualTo(3);
			expect(row2.toString()).isEqualTo("elephant,lion,monkey");
		
			expect(row3.getElementCount()).isEqualTo(3);
			expect(row3.toString()).isEqualTo("flower,tree,palm");
	}
	
	//test case
	public void testCase_toRightRotatedMatrix() {
		
		//setup
			final var matrix = new Matrix<String>();
		
			matrix.addRow("apple", "banana", "cerish");
			matrix.addRow("elephant", "lion", "monkey");
			matrix.addRow("flower", "tree", "palm");
			
		//execution
		final var rightRotatedMatrix = matrix.toRightRotatedMatrix();
		
		//verification
			expect(rightRotatedMatrix.getRowCount()).isEqualTo(3);
			expect(rightRotatedMatrix.getColumnCount()).isEqualTo(3);
			
			expect(rightRotatedMatrix.getRow(1).toString()).isEqualTo("flower,elephant,apple");
			expect(rightRotatedMatrix.getRow(2).toString()).isEqualTo("tree,lion,banana");
			expect(rightRotatedMatrix.getRow(3).toString()).isEqualTo("palm,monkey,cerish");
	}
}
