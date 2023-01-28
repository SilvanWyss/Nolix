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
final class GapMatrixIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> GapMatrixIterator<E2> forGapMatrix(final GapMatrix<E2> gapMatrix) {
		return new GapMatrixIterator<>(gapMatrix);
	}
	
	//attribute
	private final GapMatrix<E> parentGapMatrix;
	
	//attribute
	private int nextElementRowIndex = -1;
	
	//attribute
	private int nextElementColumnIndex = -1;
	
	//constructor
	private GapMatrixIterator(final GapMatrix<E> parentGapMatrix) {
		
		GlobalValidator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();
		
		this.parentGapMatrix = parentGapMatrix;
		
		incrementNextElementRowAndColumnIndex();
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextElementRowIndex != -1);
	}
	
	//method
	@Override
	public E next() {
		
		assertHasNextElement();
				
		return nextWhenHasNext();
	}
	
	//method
	private void assertHasNextElement() throws NoSuchElementException {
		if (!hasNext()) {
			throw
			ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
			.toNoSuchElementException();
		}
	}
	
	//method
	private void incrementNextElementRowAndColumnIndex() {
		
		nextElementColumnIndex++;
		
		while (nextElementRowIndex <= parentGapMatrix.getRowCount()) {
			
			while (nextElementColumnIndex <= parentGapMatrix.getColumnCount()) {
				
				if (parentGapMatrix.containsAt1BasedRowIndexAndColumnIndex(nextElementRowIndex, nextElementColumnIndex)) {
					return;
				}
				
				nextElementColumnIndex++;
			}
					
			nextElementRowIndex++;
			nextElementColumnIndex = 1;
		}
		
		nextElementRowIndex = -1;
		nextElementColumnIndex = -1;
	}
	
	//method
	private E nextWhenHasNext() {
		
		final var element = parentGapMatrix.getRefAt1BasedRowIndexAndColumnIndex(nextElementRowIndex, nextElementColumnIndex);
		
		incrementNextElementRowAndColumnIndex();
		
		return element;
	}
}
