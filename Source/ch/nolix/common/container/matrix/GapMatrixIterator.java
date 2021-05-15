//package declaration
package ch.nolix.common.container.matrix;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;

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
		
		Validator.assertThat(parentGapMatrix).thatIsNamed("parent GapMatrix").isNotNull();
		
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
