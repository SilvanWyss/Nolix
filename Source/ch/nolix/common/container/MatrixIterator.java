//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ErrorPredicate;
import ch.nolix.common.zetaValidator.ZetaValidator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 60
 * @param <E> - The type of the elements of the matrix of a matrix iterator.
 */
final class MatrixIterator<E> implements Iterator<E> {

	//attributes
	private final Matrix<E> matrix;
	private int nextElementIndex = 1;
	
	//constructor
	/**
	 * Creates new matrix iterator for the given matrix.
	 * 
	 * @param matrix
	 * @throws NullArgumentException if the given matrix is null,
	 */
	public MatrixIterator(final Matrix<E> matrix) {
		
		//Checks if the given matrix is not null.
		ZetaValidator.supposeThat(matrix).thatIsInstanceOf(Matrix.class).isNotNull();
		
		//Sets the matrix of this matrix iterator.
		this.matrix = matrix;
	}
	
	//method
	/**
	 * @return true if this matrix iterator has a next element.
	 */
	public final boolean hasNext() {
		return (nextElementIndex <= matrix.getSize());
	}
	
	//method
	/**
	 * @return the next element of this matrix iterator.
	 * @throws InvalidArgumentException if this matrix iterator has no next element.
	 */
	public final E next() {
		
		//Checks if this matrix iterator has a next element.
		if (!hasNext()) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("has no next element")
			);
		}
		
		return matrix.getRefAt(nextElementIndex++);
	}
}
