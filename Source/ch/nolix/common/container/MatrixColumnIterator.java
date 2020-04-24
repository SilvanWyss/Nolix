//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
final class MatrixColumnIterator<E> implements Iterator<E> {

	//attributes
	private final MatrixColumn<E> parentMatrixColumn;
	private int nextElementRowIndex = 1;
	
	//constructor
	public MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {
		
		Validator
		.assertThat(parentMatrixColumn)
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
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.NEXT_ELEMENT
			);
		}
	}
}
