//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

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
	@Override
	public boolean hasNext() {
		return (nextElementRowIndex <= parentMatrixColumn.getSize());
	}

	//method
	@Override
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
