//package declaration
package ch.nolix.core.container.matrix;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerComparableGetter;

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
	public E getRefLast() {
		return parentMatrix.getRefAt(parentMatrix.getRowCount(), getColumnIndex());
	}
	
	//method
	@Override
	public MatrixColumnIterator<E> iterator() {
		return new MatrixColumnIterator<>(this);
	}
	
	//method
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
