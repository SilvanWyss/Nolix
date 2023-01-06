//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class MatrixRowIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> MatrixRowIterator<E2> forMatrixRow(final MatrixRow<E2> matrixRow) {
		return new MatrixRowIterator<>(matrixRow);
	}
	
	//attribute
	private final MatrixRow<E> parentMatrixRow;
	
	//attribute
	private int nextElement1BasedColumnIndex = 1;
	
	//constructor
	private MatrixRowIterator(final MatrixRow<E> parentMatrixRow) {
		
		GlobalValidator.assertThat(parentMatrixRow).thatIsNamed("parent MatrixRow").isNotNull();
		
		this.parentMatrixRow = parentMatrixRow;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextElement1BasedColumnIndex <= parentMatrixRow.getElementCount());
	}
	
	//method
	@Override
	public E next() {
		
		assertHasNext();
		
		return nextWhenHasNext();
	}
	
	//method
	private void assertHasNext() throws NoSuchElementException {
		if (!hasNext()) {
			throw
			ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
			.toNoSuchElementException();
		}
	}
	
	//method
	private E nextWhenHasNext() {
		
		final var element = parentMatrixRow.getRefAt1BasedIndex(nextElement1BasedColumnIndex);
		
		nextElement1BasedColumnIndex++;
		
		return element;
	}
}
