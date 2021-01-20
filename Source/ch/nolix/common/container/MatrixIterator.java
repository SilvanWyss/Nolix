//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2016-08-1
 * @lines 80
 * @param <E> is the type of the elements of a {@link MatrixIterator}.
 */
final class MatrixIterator<E> implements Iterator<E> {

	//attributes
	private final Matrix<E> parentMatrix;
	private int nextElementIndex = 1;
	
	//constructor
	/**
	 * Creates a new {@link MatrixIterator} for the given matrix.
	 * 
	 * @param parentMatrix
	 * @throws ArgumentIsNullException if the given parent matrix is null.
	 */
	public MatrixIterator(final Matrix<E> parentMatrix) {
		
		//Asserts that the given parent matrix is not null.
		Validator
		.assertThat(parentMatrix)
		.thatIsNamed("parent matrix")
		.isNotNull();
		
		//Sets the parent matrix of the current matrix iterator.
		this.parentMatrix = parentMatrix;
	}
	
	//method
	/**
	 * @return true if the current {@link MatrixIterator} has a next element.
	 */
	@Override
	public boolean hasNext() {
		return (nextElementIndex <= parentMatrix.getElementCount());
	}
	
	//method
	/**
	 * @return the next element of the current {@link MatrixIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link MatrixIterator} does not have a next element.
	 */
	@Override
	public E next() {
		
		//Asserts that this matrix iterator has a next element.
		supposeHasNextElement();
		
		final var element = parentMatrix.getRefAt(nextElementIndex);
		nextElementIndex++;
		return element;
	}

	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link MatrixIterator} does not have a next element.
	 */
	private void supposeHasNextElement() {
		
		//Asserts that this matrix iterator has a next element.
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
	}
}
