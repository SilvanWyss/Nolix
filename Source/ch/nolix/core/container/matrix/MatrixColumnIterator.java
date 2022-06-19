//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class MatrixColumnIterator<E> implements Iterator<E> {

	//attributes
	private final MatrixColumn<E> parentMatrixColumn;
	private int nextElementRowIndex = 1;
	
	//constructor
	public MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {
		
		GlobalValidator
		.assertThat(parentMatrixColumn)
		.thatIsNamed("parent matrix column")
		.isNotNull();
		
		this.parentMatrixColumn = parentMatrixColumn;
	}

	//method
	@Override
	public boolean hasNext() {
		return (nextElementRowIndex <= parentMatrixColumn.getElementCount());
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
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
				this,
				LowerCaseCatalogue.NEXT_ELEMENT
			);
		}
	}
}
