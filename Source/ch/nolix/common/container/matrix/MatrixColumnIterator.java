//package declaration
package ch.nolix.common.container.matrix;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;

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
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				LowerCaseCatalogue.NEXT_ELEMENT
			);
		}
	}
}
