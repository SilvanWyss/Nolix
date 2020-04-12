//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
final class MatrixRowIterator<E> implements Iterator<E> {

	//attributes
	private final MatrixRow<E> parentMatrixRow;
	private int nextElementColumnIndex = 1;
	
	//constructor
	public MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {
		
		Validator
		.assertThat(parentMatrixRow)
		.thatIsNamed("parent matrix row")
		.isNotNull();
		
		this.parentMatrixRow = parentMatrixRow;
	}

	//method
	@Override
	public boolean hasNext() {
		return (nextElementColumnIndex <= parentMatrixRow.getSize());
	}

	//method
	@Override
	public E next() {
		
		supposeHasNextElement();
		
		final var element = parentMatrixRow.getRefAt(nextElementColumnIndex);
		nextElementColumnIndex++;
		return element;
	}

	//method
	private void supposeHasNextElement() {
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
	}
}
