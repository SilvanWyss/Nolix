//package declaration
package ch.nolix.common.containers;

//own imports
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class MatrixColumn<E> implements IContainer<E> {

	//attributes
	private final Matrix<E> parentMatrix;
	private final int columnIndex;
	
	//constructor
	MatrixColumn(final Matrix<E> parentMatrix, final int columnIndex) {
		
		Validator
		.assertThat(parentMatrix)
		.thatIsNamed("parent matrix")
		.isNotNull();
		
		Validator
		.assertThat(columnIndex)
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
		return new MatrixColumnIterator<>(this);
	}
	
	//method
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
