//package declaration
package ch.nolix.common.containers;

//own imports
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class MatrixRow<E> implements IContainer<E> {

	//attributes
	private final Matrix<E> parentMatrix;
	private final int rowIndex;
	
	//constructor
	MatrixRow(final Matrix<E> parentMatrix, final int rowIndex) {
		
		Validator
		.suppose(parentMatrix)
		.thatIsNamed("parent matrix")
		.isNotNull();
		
		Validator
		.suppose(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		this.parentMatrix = parentMatrix;
		this.rowIndex = rowIndex;
	}
	
	//method
	@Override
	public int getSize() {
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
