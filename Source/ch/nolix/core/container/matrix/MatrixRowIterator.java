//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class MatrixRowIterator<E> implements Iterator<E> {

	//attributes
	private final MatrixRow<E> parentMatrixRow;
	private int nextElementColumnIndex = 1;
	
	//constructor
	public MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {
		
		GlobalValidator
		.assertThat(parentMatrixRow)
		.thatIsNamed("parent matrix row")
		.isNotNull();
		
		this.parentMatrixRow = parentMatrixRow;
	}

	//method
	@Override
	public boolean hasNext() {
		return (nextElementColumnIndex <= parentMatrixRow.getElementCount());
	}

	//method
	@Override
	public E next() {
		
		supposeHasNextElement();
		
		final var element = parentMatrixRow.getRefAt1BasedIndex(nextElementColumnIndex);
		nextElementColumnIndex++;
		return element;
	}

	//method
	private void supposeHasNextElement() {
		if (!hasNext()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
				this,
				LowerCaseCatalogue.NEXT_ELEMENT
			);
		}
	}
}
