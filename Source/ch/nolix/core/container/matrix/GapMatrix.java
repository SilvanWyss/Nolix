//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Arrays;
import java.util.Iterator;

import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerComparableGetter;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//class
public final class GapMatrix<E> extends Container<E> implements Clearable {
	
	//attributes
	private int columnCount;
	private int elementCount;
	
	//multi-attribute
	private Object[][] rows = new Object[0][0];
	
	//constructor
	public GapMatrix() {}
	
	//constructor
	public GapMatrix(final int rowCount, final int columnCount) {
		
		GlobalValidator.assertThat(rowCount).thatIsNamed(LowerCaseCatalogue.ROW_COUNT).isNotNegative();
		GlobalValidator.assertThat(columnCount).thatIsNamed(LowerCaseCatalogue.COLUMN_COUNT).isNotNegative();
		
		rows = new Object[rowCount][columnCount];
		this.columnCount = columnCount;
	}
	
	//method
	public void addColumn() {
		
		final var rowCount = getRowCount();
		
		for (var i = 0; i < rowCount; i++) {			
			rows[i] = Arrays.copyOf(rows[i], columnCount + 1);
		}
		
		columnCount++;
	}
	
	//method
	public void addRow() {
				
		final var newRowCount = getRowCount() + 1;	
		
		rows = Arrays.copyOf(rows, newRowCount);
		rows[newRowCount - 1] = new Object[getColumnCount()];
	}
	
	//method
	@Override
	public void clear() {
		rows = new Object[0][];
		columnCount = 0;
		elementCount = 0;
	}
	
	//method
	public boolean containsAt(final int rowIndex, final int columnIndex) {
		return
		rowIndex > 0
		&& rowIndex <= getRowCount()
		&& columnIndex > 0
		&& columnIndex <= getColumnCount()
		&& rows[rowIndex - 1][columnIndex - 1] != null;
	}
	
	//method
	public int getColumnCount() {
		return columnCount;
	}
	
	//method
	public int getColumnIndexOf(final int index) {
		
		//Asserts that the current matrix contains an element at the given index.
		assertContainsAt(index);
		
		return ((index - 1) % getColumnCount() + 1);
	}
	
	//method
	@Override
	public int getElementCount() {
		return elementCount;
	}
	
	//method
	@Override
	public E getRefAt(final int index) {
		return getRefAt(getRowIndexOf(index), getColumnIndexOf(index));
	}
	
	//method
	@SuppressWarnings("unchecked")
	public E getRefAt(final int rowIndex, final int columnIndex) {
		
		assertContainsAt(rowIndex, columnIndex);
		
		return (E)rows[rowIndex - 1][columnIndex - 1];
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E getRefLast() {
		
		final var rowCount = getRowCount();
		
		for (var rowIndex = rowCount - 1; rowIndex >= 0; rowIndex--) {
			for (var columnIndex = columnCount - 1; columnIndex >= 0; columnIndex--) {
				if (rows[rowIndex][columnIndex] != null) {
					return (E)rows[rowIndex][columnIndex];
				}
			}
		}
		
		throw EmptyArgumentException.forArgument(this);
	}
	
	//method
	public int getRowCount() {
		return rows.length;
	}
	
	public int getRowIndexOf(final int index) {
		
		//Asserts that the current matrix contains an element at the given index.
		assertContainsAt(index);
		
		return ((index - 1) / getColumnCount() + 1);
	}
	
	//method
	public int getSize() {
		return (getRowCount() * getColumnCount());
	}
		
	//method
	public void insert(final int rowIndex, final int columnIndex, final E element) {
		
		GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertCanContainElementAt(rowIndex, columnIndex);
		
		if (!containsAt(rowIndex, columnIndex)) {
			elementCount++;
		}
		
		rows[rowIndex - 1][columnIndex - 1] = element;
	}
	
	//method
	public Iterator<E> iterator() {
		return new GapMatrixIterator<>(this);
	}
	
	//method
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	private void assertCanContainElementAt(final int rowIndex, final int columnIndex) {
		
		GlobalValidator
		.assertThat(rowIndex)
		.thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
		.isPositive();
		
		GlobalValidator
		.assertThat(rowIndex)
		.thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
		.isNotBiggerThan(getRowCount());
		
		GlobalValidator
		.assertThat(columnIndex)
		.thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
		.isPositive();
		
		GlobalValidator
		.assertThat(columnIndex)
		.thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
		.isNotBiggerThan(getColumnCount());
	}
	
	//method
	private void assertContainsAt(final int index) {
		assertContainsAt(getRowIndexOf(index), getColumnIndexOf(index));
	}
	
	//method
	private void assertContainsAt(final int rowIndex, final int columnIndex) {
		
		assertCanContainElementAt(rowIndex, columnIndex);
		
		if (rows[rowIndex - 1][columnIndex - 1] == null) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				"(" + rowIndex + "," + columnIndex + ")",
				"is no position of an element"
			);
		}
	}
}
