package ch.nolix.coreTutorial.mathTutorial;

import ch.nolix.common.math.Matrix;

public final class MatrixMultiplicationTutorial {
	
	public static void main(String[] args) {
		
		//Creates matrix1.
		final var matrix1 =
		new Matrix(3, 3)
		.setValues(2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 1.0, 2.0, 3.0);
		
		//Creates matrix2.
		final var matrix2 =
		new Matrix(3, 3)
		.setValues(4.0, 5.0, 6.0, 1.0, 2.0, 3.0, 2.0, 3.0, 4.0);
		
		//Calculates the product of matrix1 and matrix2.
		final var product = matrix1.getProduct(matrix2);
		
		//Prints out to the console matrix1, matrix2 and the product.
		System.out.println(
			matrix1.toString()
			+ " * "
			+ matrix2.toString()
			+ " = "
			+ product.toString()
		);
	}
	
	private MatrixMultiplicationTutorial() {}
}
