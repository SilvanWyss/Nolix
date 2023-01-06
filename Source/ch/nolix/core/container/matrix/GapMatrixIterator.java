//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

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
			throw new NoSuchElementException("The current GapMatrixIterator does not have a next element.");
		}
	}
	
	//method
	private void incrementNextElementRowAndColumnIndex() {
		
		nextElementColumnIndex++;
		
		while (nextElementRowIndex <= parentGapMatrix.getRowCount()) {
			
			while (nextElementColumnIndex <= parentGapMatrix.getColumnCount()) {
				
				if (parentGapMatrix.containsAt(nextElementRowIndex, nextElementColumnIndex)) {
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
		
		final var element = parentGapMatrix.getRefAt(nextElementRowIndex, nextElementColumnIndex);
		
		incrementNextElementRowAndColumnIndex();
		
		return element;
	}
}
