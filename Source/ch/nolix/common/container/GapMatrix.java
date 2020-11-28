//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.common.validator.Validator;

//class
public final class GapMatrix<E> implements Clearable<GapMatrix<E>>, IContainer<E> {
	
	//attributes
	private int columnCount = 0;
	private int elementCount = 0;
	
	//multi-attribute
	private Object[][] rows = new Object[0][0];
	
	//constructor
	public GapMatrix() {}
	
	//constructor
	public GapMatrix(final int rowCount, final int columnCount) {
		
		Validator.assertThat(rowCount).thatIsNamed(VariableNameCatalogue.ROW_COUNT).isNotNegative();
		Validator.assertThat(columnCount).thatIsNamed(VariableNameCatalogue.COLUMN_COUNT).isNotNegative();
		
		rows = new Object[rowCount][columnCount];
		this.columnCount = columnCount;
	}
	
	//method
	public void addColumn() {
		
		final var rowCount = getRowCount();
		
		for (var i = 0; i < rowCount; i++) {
			
			final var newRow = new Object[columnCount + 1];
			
			for (var j = 0; j < columnCount; j++) {
				newRow[j] = rows[i][j];
			}
			
			rows[i] = newRow;
		}
		
		columnCount++;
	}
	
	//method
	public void addRow() {
				
		final var rowCount = getRowCount();	
		final var newRows = new Object[rowCount + 1][];	
		for (var i = 0; i < rowCount; i++) {
			newRows[i] = rows[i];
		}
		newRows[rowCount] = new Object[getColumnCount()];
		
		rows = newRows;
	}
	
	//method
	public GapMatrix<E> clear() {
		
		rows = new Object[0][];
		columnCount = 0;
		elementCount = 0;
		
		return this;
	}
	
	//method
	@Override
	public boolean containsAny() {
		return (elementCount > 0);
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
		
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		assertCanContainElementAt(rowIndex, columnIndex);
		
		if (!containsAt(rowIndex, columnIndex)) {
			elementCount++;
		}
		
		rows[rowIndex - 1][columnIndex - 1] = element;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (elementCount == 0);
	}
	
	//method
	public Iterator<E> iterator() {
		return new GapMatrixIterator<>(this);
	}
	
	//method
	private void assertCanContainElementAt(final int rowIndex, final int columnIndex) {
		
		Validator
		.assertThat(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		Validator
		.assertThat(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isNotBiggerThan(getRowCount());
		
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isPositive();
		
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
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
			new InvalidArgumentException("(" + rowIndex + "," + columnIndex + ")", "is no position of an element");
		}
	}
}
