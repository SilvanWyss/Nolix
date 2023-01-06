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
final class MatrixColumnIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> MatrixColumnIterator<E2> forMatrixColumn(final MatrixColumn<E2> matrixColumn) {
		return new MatrixColumnIterator<>(matrixColumn);
	}
	
	//attribute
	private final MatrixColumn<E> parentMatrixColumn;
	
	//attribute
	private int nextElement1BasedRowIndex = 1;
	
	//constructor
	private MatrixColumnIterator(final MatrixColumn<E> parentMatrixColumn) {
		
		GlobalValidator.assertThat(parentMatrixColumn).thatIsNamed("parent MatrixColumn").isNotNull();
		
		this.parentMatrixColumn = parentMatrixColumn;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextElement1BasedRowIndex <= parentMatrixColumn.getElementCount());
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
		
		final var element = parentMatrixColumn.getRefAt1BasedIndex(nextElement1BasedRowIndex);
		
		nextElement1BasedRowIndex++;
		
		return element;
	}
}
