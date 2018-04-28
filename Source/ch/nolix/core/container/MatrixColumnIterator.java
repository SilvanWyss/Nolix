//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class MatrixColumnIterator<E> implements Iterator<E> {

	//attributes
	private final MatrixColumn<E> parentMatrixColumn;
	private int nextElementRowIndex = 1;
	
	//constructor
	public MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {
		
		Validator
		.suppose(parentMatrixColumn)
		.thatIsNamed("parent matrix column")
		.isNotNull();
		
		this.parentMatrixColumn = parentMatrixColumn;
	}

	//method
	public boolean hasNext() {
		return (nextElementRowIndex <= parentMatrixColumn.getElementCount());
	}

	//method
	public E next() {
		
		supposeHasNextElement();
		
		final var element = parentMatrixColumn.getRefAt(nextElementRowIndex);
		nextElementRowIndex++;
		return element;
	}

	//method
	private void supposeHasNextElement() {
		if (!hasNext()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
	}
}
