//package declaration
package ch.nolix.coreTest.mathematicsTest;

//own imports
import ch.nolix.core.mathematics.Matrix;
import ch.nolix.core.test2.ZetaTest;

//test class
/**
* This class is a test class for the matrix class.
* 
* @author Silvan Wyss
* @month 2016-06
* @lines 270
*/
public final class MatrixTest extends ZetaTest {
	
	//loop test method
	public void loopTest_createIdendityMatrix() {
				
		//number of main loop iterations
		final int n = 100;
		
		//main loop
		for (int i = 1; i <= n; i++) {
			
			//execution
			final Matrix matrix = Matrix.createIdendityMatrix(i);
			
			//verification
			expectThat(matrix.getRowCount()).equals(i);
			expectThat(matrix.getColumnCount()).equals(i);
		}
	}
	
	//loop test method
	public void loopTest_getRank() {
		
		//number of main loop iterations
		final int n = 10;
		
		//main loop
		for (int i = 1; i <= n; i++) {
			
			//setup
			final Matrix matrix = Matrix.createIdendityMatrix(i);
			
			//execution and verification
			expectThat(matrix.getRank()).equals(i);
		}
	}
	
	//loop test method
	public void loop_testGetTrace() {
		
		//number of main loop iterations
		final int n = 100;
		
		//main loop
		for (int i = 1; i <= n; i++) {
			
			//setup
			final Matrix matrix = Matrix.createIdendityMatrix(i);
			
			//execution and verification
			expectThat(matrix.getTrace()).equals(i);
		}
	}

	//test method
	public void test_Add() {
		
		//setup
		final Matrix matrix1 = new Matrix(2, 3).setValues(1.0, 1.0, 1.0, 2.0, 2.0, 2.0);
		final Matrix matrix2 = new Matrix(2, 3).setValues(5.0, 5.0, 5.0, 6.0, 6, 6.0);
		
		//execution
		final Matrix sum = matrix1.getSum(matrix2);
		
		//verification
		final Matrix expectedMatrix = new Matrix(2, 3).setValues(6.0, 6.0, 6.0, 8.0, 8.0, 8.0);
		expectThat(sum).equals(expectedMatrix);
	}
	
	//test method
	public void test_AppendAtRight() {
		
		//setup
		final Matrix matrix1 = new Matrix(2, 2).setValues(1.0, 1.0, 2.0, 2.0);
		final Matrix matrix2 = new Matrix(2, 2).setValues(5.0, 5.0, 6.0, 6.0);

		//execution
		matrix1.appendAtRight(matrix2);
		
		//verification
		final Matrix expectedMatrix = new Matrix(2, 4).setValues(1.0, 1.0, 5.0, 5.0, 2.0, 2.0, 6.0, 6.0);
		expectThat(matrix1).equals(expectedMatrix);
	}
	
	//test method
	public void test_getInverse_1() {
		
		//setup
		final Matrix matrix = new Matrix(2).setValues(1.0, 2.0, 3.0, 4.0);
		
		//execution
		final Matrix inverse = matrix.getInverse();
		
		//verification
		expectThat(matrix.getProduct(inverse)).equals(Matrix.createIdendityMatrix(2));
	}
	
	//test method
	public void test_getInverse_2() {
		
		//setup
		final Matrix matrix = new Matrix(3).setValues(2.0, 6.0, 4.0, 1.0, 5.0, 9.0, 3.0, 7.0, 8.0);
			
		//execution
		final Matrix inverse = matrix.getInverse();

		//verification
		expectThat(matrix.getProduct(inverse)).withDefaultMaxDeviation().equals(Matrix.createIdendityMatrix(3));
	}
	
	//test method
	public void test_getInverse_3() {
		
		//setup
		final Matrix matrix = new Matrix(4).setValues(3.0, 1.0, 7.0, 3.0, 5.0, 9.0, 8.0, 7.0, 8.0, 6.0, 8.0, 4.0, 5.0, 9.0, 3.0, 2.0);
			
		//execution
		final Matrix inverse = matrix.getInverse();

		//verification
		expectThat(matrix.getProduct(inverse)).withDefaultMaxDeviation().equals(Matrix.createIdendityMatrix(4));
	}
	
	//test method
	public void test_getProduct() {
		
		//setup
		final Matrix matrix1 = new Matrix(2, 3).setValues(1, 1, 1, 2, 2, 2);
		final Matrix matrix2 = new Matrix(3, 2).setValues(1, 1, 2, 2, 3, 3);
		
		//execution
		final Matrix product = matrix1.getProduct(matrix2);
		
		//verification
		final Matrix expectedProduct = new Matrix(2, 2).setValues(6, 6, 12, 12);
		expectThat(product).equals(expectedProduct);
	}
	
	//test method
	public void test_getSolutionAsExtendedMatrix_1() {
		
		//setup
		final Matrix matrix = new Matrix(2, 3).setValues(4.0, 4.0, 30.0, 0.0, 2.0, 10.0);

		//execution
		final double[] solution = matrix.getSolutionAsExtendedMatrix();
		
		//verification
		expectThat(solution.length).equals(2);;
		expectThat(solution[0]).equals(2.5);
		expectThat(solution[1]).equals(5.0);
	}
	
	//test method
	public void test_getSolutionAsExtendedMatrix_2() {
		
		//setup
		final Matrix matrix = new Matrix(3, 4).setValues(1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0);
		
		//execution
		final double[] solution = matrix.getSolutionAsExtendedMatrix();
		
		//verification
		expectThat(solution.length).equals(3);;
		expectThat(solution[0]).equals(1.0);
		expectThat(solution[1]).equals(1.0);
		expectThat(solution[2]).equals(1.0);
	}
	
	//test method
	public void test_getTransposed() {
		
		//setup
		final Matrix matrix = new Matrix(4, 3).setValues(20.0, 10.0, 1.0, 10.0, 20.0, 1.0, 20.0, 10.0, 1.0, 10.0, 20.0 ,1.0);
		
		//execution
		final Matrix transposed = matrix.getTransposed();
		
		//verification
		Matrix expectedTransposed = new Matrix(3, 4).setValues(20.0, 10.0, 20.0, 10.0, 10.0, 20.0, 10.0, 20.0, 1.0, 1.0, 1.0, 1.0);
		expectThat(transposed).equals(expectedTransposed);
	}
	
	//test method
	public void test_toString_1() {
		
		//setup
		final Matrix matrix = new Matrix(1);

		//execution and verification
		expectThat(matrix.toString()).equals("[0]");
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final Matrix matrix = new Matrix(2);

		//execution and verification
		expectThat(matrix.toString()).equals("[0,0;0,0]");
	}
	
	//test method
	public void test_toString_3() {
		
		//setup
		final Matrix matrix = new Matrix(3);

		//execution and verification
		expectThat(matrix.toString()).equals("[0,0,0;0,0,0;0,0,0]");
	}
	
	//test method
	public void test_toString_4() {
		
		//setup
		final Matrix matrix = new Matrix(4);
		
		//execution and verification
		expectThat(matrix.toString()).equals("[0,0,0,0;0,0,0,0;0,0,0,0;0,0,0,0]");
	}
	
	//test method
	public void test_toString_5() {
		
		//setup
		final Matrix matrix = Matrix.createIdendityMatrix(1);
		
		//execution and verification
		expectThat(matrix.toString()).equals("[1]");
	}
	
	//test method
	public void test_toString_6() {
		
		//setup
		final Matrix matrix = Matrix.createIdendityMatrix(2);
		
		//execution and verification
		expectThat(matrix.toString()).equals("[1,0;0,1]");
	}
	
	//test method
	public void test_toString_7() {
		
		//setup
		final Matrix matrix = Matrix.createIdendityMatrix(3);
				
		//execution and verification
		expectThat(matrix.toString()).equals("[1,0,0;0,1,0;0,0,1]");
	}
	
	//test method
	public void test_toString_8() {
		
		//setup
		final Matrix matrix = Matrix.createIdendityMatrix(4);
				
		//execution and verification
		expectThat(matrix.toString()).equals("[1,0,0,0;0,1,0,0;0,0,1,0;0,0,0,1]");
	}
}
