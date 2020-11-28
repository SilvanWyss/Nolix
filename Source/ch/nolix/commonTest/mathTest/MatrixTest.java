//package declaration
package ch.nolix.commonTest.mathTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.math.Matrix;
import ch.nolix.common.test.Test;

//class
/**
* A {@link MatrixTest} is a test for {@link Matrix}es.
* 
* @author Silvan Wyss
* @month 2016-06
* @lines 270
*/
public final class MatrixTest extends Test {
	
	//method
	@TestCase
	public void loopTest_createIdendityMatrix() {
				
		//test parameters
		final var n = 100;
		
		//test loop
		for (var i = 1; i <= n; i++) {
			
			//execution
			final var matrix = Matrix.createIdendityMatrix(i);
			
			//verification
			expect(matrix.getRowCount()).isEqualTo(i);
			expect(matrix.getColumnCount()).isEqualTo(i);
		}
	}
	
	//method
	@TestCase
	public void loopTest_getRank() {
		
		//test parameters
		final var n = 10;
		
		//test loop
		for (var i = 1; i <= n; i++) {
			
			//setup
			final var matrix = Matrix.createIdendityMatrix(i);
			
			//execution & verification
			expect(matrix.getRank()).isEqualTo(i);
		}
	}
	
	//method
	@TestCase
	public void loop_testGetTrace() {
		
		//test parameters
		final var n = 100;
		
		//test loop
		for (var i = 1; i <= n; i++) {
			
			//setup
			final var matrix = Matrix.createIdendityMatrix(i);
			
			//execution & verification
			expect(matrix.getTrace()).isEqualTo(i);
		}
	}

	//method
	@TestCase
	public void testCase_add() {
		
		//setup
		final var matrix1 = new Matrix(2, 3).setValues(1.0, 1.0, 1.0, 2.0, 2.0, 2.0);
		final var matrix2 = new Matrix(2, 3).setValues(5.0, 5.0, 5.0, 6.0, 6, 6.0);
		
		//execution
		final var sum = matrix1.getSum(matrix2);
		
		//verification
		final Matrix expectedMatrix = new Matrix(2, 3).setValues(6.0, 6.0, 6.0, 8.0, 8.0, 8.0);
		expect(sum).isEqualTo(expectedMatrix);
	}
	
	//method
	@TestCase
	public void testCase_appendAtRight() {
		
		//setup
		final var matrix1 = new Matrix(2, 2).setValues(1.0, 1.0, 2.0, 2.0);
		final var matrix2 = new Matrix(2, 2).setValues(5.0, 5.0, 6.0, 6.0);

		//execution
		matrix1.appendAtRight(matrix2);
		
		//verification
		final var expectedMatrix = new Matrix(2, 4).setValues(1.0, 1.0, 5.0, 5.0, 2.0, 2.0, 6.0, 6.0);
		expect(matrix1).isEqualTo(expectedMatrix);
	}
	
	//method
	@TestCase
	public void testCase_getInverse_1() {
		
		//setup
		final var matrix = new Matrix(2).setValues(1.0, 2.0, 3.0, 4.0);
		
		//execution
		final Matrix inverse = matrix.getInverse();
		
		//verification
		expect(matrix.getProduct(inverse)).isEqualTo(Matrix.createIdendityMatrix(2));
	}
	
	//method
	@TestCase
	public void testCase_getInverse_2() {
		
		//setup
		final var matrix = new Matrix(3).setValues(2.0, 6.0, 4.0, 1.0, 5.0, 9.0, 3.0, 7.0, 8.0);
			
		//execution
		final var inverse = matrix.getInverse();

		//verification
		expect(matrix.getProduct(inverse)).withDefaultMaxDeviation().isEqualTo(Matrix.createIdendityMatrix(3));
	}
	
	//method
	@TestCase
	public void testCase_getInverse_3() {
		
		//setup
		final var matrix =
		new Matrix(4)
		.setValues(3.0, 1.0, 7.0, 3.0, 5.0, 9.0, 8.0, 7.0, 8.0, 6.0, 8.0, 4.0, 5.0, 9.0, 3.0, 2.0);
		
		//execution
		final var inverse = matrix.getInverse();

		//verification
		expect(matrix.getProduct(inverse)).withDefaultMaxDeviation().isEqualTo(Matrix.createIdendityMatrix(4));
	}
	
	//method
	@TestCase
	public void testCase_getProduct() {
		
		//setup
		final var matrix1 = new Matrix(2, 3).setValues(1, 1, 1, 2, 2, 2);
		final var matrix2 = new Matrix(3, 2).setValues(1, 1, 2, 2, 3, 3);
		
		//execution
		final Matrix product = matrix1.getProduct(matrix2);
		
		//verification
		final Matrix expectedProduct = new Matrix(2, 2).setValues(6, 6, 12, 12);
		expect(product).isEqualTo(expectedProduct);
	}
	
	//method
	@TestCase
	public void testCase_getSolutionAsExtendedMatrix_1() {
		
		//setup
		final var matrix = new Matrix(2, 3).setValues(4.0, 4.0, 30.0, 0.0, 2.0, 10.0);

		//execution
		final double[] solution = matrix.getSolutionAsExtendedMatrix();
		
		//verification
		expect(solution.length).isEqualTo(2);
		expect(solution[0]).isEqualTo(2.5);
		expect(solution[1]).isEqualTo(5.0);
	}
	
	//method
	@TestCase
	public void testCase_getSolutionAsExtendedMatrix_2() {
		
		//setup
		final var matrix = new Matrix(3, 4).setValues(1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0);
		
		//execution
		final double[] solution = matrix.getSolutionAsExtendedMatrix();
		
		//verification
		expect(solution.length).isEqualTo(3);
		expect(solution[0]).isEqualTo(1.0);
		expect(solution[1]).isEqualTo(1.0);
		expect(solution[2]).isEqualTo(1.0);
	}
	
	//method
	@TestCase
	public void testCase_getTransposed() {
		
		//setup
		final var matrix = new Matrix(4, 3).setValues(20.0, 10.0, 1.0, 10.0, 20.0, 1.0, 20.0, 10.0, 1.0, 10.0, 20.0 ,1.0);
		
		//execution
		final var transposed = matrix.getTransposed();
		
		//verification
		final var expectedTransposed =
		new Matrix(3, 4)
		.setValues(20.0, 10.0, 20.0, 10.0, 10.0, 20.0, 10.0, 20.0, 1.0, 1.0, 1.0, 1.0);
		expect(transposed).isEqualTo(expectedTransposed);
	}
	
	//method
	@TestCase
	public void testCase_toString_1() {
		
		//setup
		final var matrix = new Matrix(1);

		//execution & verification
		expect(matrix.toString()).isEqualTo("[0]");
	}
	
	//method
	@TestCase
	public void testCase_toString_2() {
		
		//setup
		final var matrix = new Matrix(2);

		//execution & verification
		expect(matrix.toString()).isEqualTo("[0,0;0,0]");
	}
	
	//method
	@TestCase
	public void testCase_toString_3() {
		
		//setup
		final var matrix = new Matrix(3);

		//execution & verification
		expect(matrix.toString()).isEqualTo("[0,0,0;0,0,0;0,0,0]");
	}
	
	//method
	@TestCase
	public void testCase_toString_4() {
		
		//setup
		final var matrix = new Matrix(4);
		
		//execution & verification
		expect(matrix.toString()).isEqualTo("[0,0,0,0;0,0,0,0;0,0,0,0;0,0,0,0]");
	}
	
	//method
	@TestCase
	public void testCase_toString_5() {
		
		//setup
		final var matrix = Matrix.createIdendityMatrix(1);
		
		//execution & verification
		expect(matrix.toString()).isEqualTo("[1]");
	}
	
	//method
	@TestCase
	public void testCase_toString_6() {
		
		//setup
		final var matrix = Matrix.createIdendityMatrix(2);
		
		//execution & verification
		expect(matrix.toString()).isEqualTo("[1,0;0,1]");
	}
	
	//method
	@TestCase
	public void testCase_toString_7() {
		
		//setup
		final var matrix = Matrix.createIdendityMatrix(3);
				
		//execution & verification
		expect(matrix.toString()).isEqualTo("[1,0,0;0,1,0;0,0,1]");
	}
	
	//method
	@TestCase
	public void testCase_toString_8() {
		
		//setup
		final var matrix = Matrix.createIdendityMatrix(4);
				
		//execution & verification
		expect(matrix.toString()).isEqualTo("[1,0,0,0;0,1,0,0;0,0,1,0;0,0,0,1]");
	}
}
