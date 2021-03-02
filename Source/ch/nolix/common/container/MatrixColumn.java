//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

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
		.thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
		.isPositive();
		
		this.parentMatrix = parentMatrix;
		this.columnIndex = columnIndex;
	}
	
	//method
	@Override
	public int getElementCount() {
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
