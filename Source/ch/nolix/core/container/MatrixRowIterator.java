//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//package-visible class
final class MatrixRowIterator<E> implements Iterator<E> {

	//attributes
	private final MatrixRow<E> parentMatrixRow;
	private int nextElementColumnIndex = 1;
	
	//constructor
	public MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {
		
		Validator
		.suppose(parentMatrixRow)
		.thatIsNamed("parent matrix row")
		.isInstance();
		
		this.parentMatrixRow = parentMatrixRow;
	}

	//method
	public boolean hasNext() {
		return (nextElementColumnIndex <= parentMatrixRow.getSize());
	}

	//method
	public E next() {
		
		supposeHasNextElement();
		
		final var element = parentMatrixRow.getRefAt(nextElementColumnIndex);
		nextElementColumnIndex++;
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
