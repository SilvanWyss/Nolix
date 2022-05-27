//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class GapMatrixIterator<E> implements Iterator<E> {
	
	//attributes
	private final GapMatrix<E> parentGapMatrix;
	private final int rowCount;
	private final int columnCount;
	private int nextElementRow = -1;
	private int nextElementColumn = -1;
	
	//constructor
	public GapMatrixIterator(final GapMatrix<E> parentGapMatrix) {
		
		GlobalValidator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();
		
		this.parentGapMatrix = parentGapMatrix;
		rowCount = parentGapMatrix.getRowCount();
		columnCount = parentGapMatrix.getColumnCount();
		
		updateNextElementPosition();
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (nextElementRow != -1);
	}
	
	//method
	@Override
	public E next() {
		
		assertHasNextElement();
				
		final var element = parentGapMatrix.getRefAt(nextElementRow, nextElementColumn);
		
		updateNextElementPosition();
		
		return element;
	}
	
	//method
	private void assertHasNextElement() {
		if (!hasNext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.NEXT_ELEMENT);
		}
	}
	
	//method
	private void updateNextElementPosition() {
		
		nextElementColumn++;
		
		while (nextElementRow <= rowCount) {
			
			while (nextElementColumn <= columnCount) {
				
				if (parentGapMatrix.containsAt(nextElementRow, nextElementColumn)) {
					return;
				}
				
				nextElementColumn++;
			}
						
			nextElementRow++;
			nextElementColumn = 1;
		}
		
		nextElementRow = -1;
		nextElementColumn = -1;
	}
}
