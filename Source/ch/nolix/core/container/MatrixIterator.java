//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
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
	 * @throws NullArgumentException if the given parent matrix is not an instance.
	 */
	public MatrixIterator(final Matrix<E> parentMatrix) {
		
		//Checks if the given parent matrix is an instance.
		Validator
		.suppose(parentMatrix)
		.thatIsNamed("parent matrix")
		.isInstance();
		
		//Sets the parent matrix of the current matrix iterator.
		this.parentMatrix = parentMatrix;
	}
	
	//method
	/**
	 * @return true if the current {@link MatrixIterator} has a next element.
	 */
	public final boolean hasNext() {
		return (nextElementIndex <= parentMatrix.getSize());
	}
	
	//method
	/**
	 * @return the next element of the current {@link MatrixIterator}.
	 * @throws UnexistingAttributeException
	 * if the current {@link MatrixIterator} has no next element.
	 */
	public final E next() {
		
		//Checks if this matrix iterator has a next element.
		supposeHasNextElement();
		
		final var element = parentMatrix.getRefAt(nextElementIndex);
		nextElementIndex++;
		return element;
	}

	//method
	/**
	 * @throws UnexistingAttributeException
	 * if the current {@link MatrixIterator} has no next element.
	 */
	private void supposeHasNextElement() {
		
		//Checks if this matrix iterator has a next element.
		if (!hasNext()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
	}
}
