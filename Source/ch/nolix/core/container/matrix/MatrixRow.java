//package declaration
package ch.nolix.core.container.matrix;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.container.Container;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementTakerComparableGetter;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class MatrixRow<E> extends Container<E> {

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
	public E getRefLast() {
		return parentMatrix.getRefAt(getRowIndex(), parentMatrix.getColumnCount());
	}
	
	//method
	@Override
	public MatrixRowIterator<E> iterator() {
		return new MatrixRowIterator<>(this);
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
