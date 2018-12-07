//package declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator2.Validator;

//class
public final class MatrixColumn<E> implements IContainer<E> {

	//attributes
	private final Matrix<E> parentMatrix;
	private final int columnIndex;
	
	//package-visible constructor
	MatrixColumn(final Matrix<E> parentMatrix, final int columnIndex) {
		
		Validator
		.suppose(parentMatrix)
		.thatIsNamed("parent matrix")
		.isInstance();
		
		Validator
		.suppose(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isPositive();
		
		this.parentMatrix = parentMatrix;
		this.columnIndex = columnIndex;
	}
	
	//method
	@Override
	public int getSize() {
		return parentMatrix.getRowCount();
	}
	
	//method
	public int getColumnIndex() {
		return columnIndex;
	}
	
	//method
	@Override
	public E getRefAt(final int rowIndex) {
		return parentMatrix.getRefAt(rowIndex, getColumnIndex());
	}

	//method
	@Override
	public MatrixColumnIterator<E> iterator() {
		return new MatrixColumnIterator<E>(this);
	}
	
	//method
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
