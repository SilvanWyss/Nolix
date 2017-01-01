/*
 * file:	MatrixTest.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.commonTest.containerTest;

//own imports
import ch.nolix.common.container.Matrix;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the matrix class.
 */
public final class MatrixTest extends ZetaTest {

	//test method
	public final void testAddRow() {
		
		final Matrix<String> matrix = new Matrix<String>().addRow("apple", "banana", "cerish");
		
		expectThat(matrix.getRowCount()).equals(1);
		expectThat(matrix.getColumnCount()).equals(3);
		expectThat(matrix.toString()).equals("apple,banana,cerish");
	}
}
