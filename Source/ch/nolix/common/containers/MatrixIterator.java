//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 80
 * @param <E> The type of the elements of a {@link MatrixIterator}.
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
		
		//Checks if the given parent matrix is not null.
		Validator
		.suppose(parentMatrix)
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
	public final boolean hasNext() {
		return (nextElementIndex <= parentMatrix.getSize());
	}
	
	//method
	/**
	 * @return the next element of the current {@link MatrixIterator}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link MatrixIterator} does not have a next element.
	 */
	@Override
	public final E next() {
		
		//Checks if this matrix iterator has a next element.
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
		
		//Checks if this matrix iterator has a next element.
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
	}
}
