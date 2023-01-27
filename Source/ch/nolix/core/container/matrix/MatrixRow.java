//package declaration
package ch.nolix.core.container.matrix;

import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerComparableGetter;

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
	public E getRefAt1BasedIndex(final int columnIndex) {
		return parentMatrix.getRefAt1BasedRowIndexAndColumnIndex(getRowIndex(), columnIndex);
	}
	
	//method
	@Override
	public E getRefLast() {
		return parentMatrix.getRefAt1BasedRowIndexAndColumnIndex(getRowIndex(), parentMatrix.getColumnCount());
	}
	
	//method
	@Override
	public MatrixRowIterator<E> iterator() {
		return MatrixRowIterator.forMatrixRow(this);
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
