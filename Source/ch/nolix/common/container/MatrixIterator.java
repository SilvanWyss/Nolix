/*
 * file:	MatrixIterator.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	50
 */

//package declaration
package ch.nolix.common.container;

//own import
import ch.nolix.common.util.Validator;

//package-visible class
final class MatrixIterator<E> implements java.util.Iterator<E> {

	//attributes
	private final Matrix<E> matrix;
	private int nextElementIndex = 1;
	
	//constructor
	/**
	 * Creates new matrix iterator for the given matrix.
	 * 
	 * @param matrix
	 * @throws Exception if the given matrix is null
	 */
	public MatrixIterator(Matrix<E> matrix) {
		
		Validator.throwExceptionIfValueIsNull("matrix", matrix);
		
		this.matrix = matrix;
	}
	
	//method
	/**
	 * @return true of this matrix iterator has a next element
	 */
	public final boolean hasNext() {
		return (nextElementIndex <= matrix.getSize());
	}
	
	//method
	/**
	 * @return the next element of this matrix iterator
	 * @throws Exception if this matrix iterator has no next element
	 */
	public final E next() {
		try {
			return matrix.getRefAt(nextElementIndex++);
		}
		catch (Exception expection) {
			throw new RuntimeException("Matrix iterator has no next element.");
		}
	}
}
