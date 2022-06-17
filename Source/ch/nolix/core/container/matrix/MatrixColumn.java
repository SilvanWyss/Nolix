//package declaration
package ch.nolix.core.container.matrix;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.container.Container;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class MatrixColumn<E> extends Container<E> {

	//attributes
	private final Matrix<E> parentMatrix;
	private final int columnIndex;
	
	//constructor
	MatrixColumn(final Matrix<E> parentMatrix, final int columnIndex) {
		
		GlobalValidator
		.assertThat(parentMatrix)
		.thatIsNamed("parent matrix")
		.isNotNull();
		
		GlobalValidator
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
