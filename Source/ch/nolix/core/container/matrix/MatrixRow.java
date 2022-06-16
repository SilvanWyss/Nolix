//package declaration
package ch.nolix.core.container.matrix;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public final class MatrixRow<E> implements IContainer<E> {

	//attributes
	private final Matrix<E> parentMatrix;
	private final int rowIndex;
	
	//constructor
	MatrixRow(final Matrix<E> parentMatrix, final int rowIndex) {
		
		GlobalValidator
		.assertThat(parentMatrix)
		.thatIsNamed("parent matrix")
		.isNotNull();
		
		GlobalValidator
		.assertThat(rowIndex)
		.thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
		.isPositive();
		
		this.parentMatrix = parentMatrix;
		this.rowIndex = rowIndex;
	}
	
	//method
	@Override
	public int getElementCount() {
		return parentMatrix.getColumnCount();
	}
	
	//method
	public int getRowIndex() {
		return rowIndex;
	}
	
	//method
	@Override
	public E getRefAt(final int columnIndex) {
		return parentMatrix.getRefAt(getRowIndex(), columnIndex);
	}
	
	//method
	@Override
	public MatrixRowIterator<E> iterator() {
		return new MatrixRowIterator<>(this);
	}
	
	//method
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
