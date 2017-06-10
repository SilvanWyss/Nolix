//package-declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A matrix is a container that stores elements in rows and columns.
 * A matrix is clearable.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 370
 * @param <E> - The type of the elements of a matrix.
 */
public final class Matrix<E> implements IContainer<E>, Clearable {

	//multiple attribute
	private Object[][] elements;
	
	//method
	/**
	 * Adds a new column to this matrix with the given elements.
	 * The complexity of this method is O(n) if this matrix contains n rows.
	 * 
	 * @param elements
	 * @return this matrix.
	 * @throws EmptyArgumentException if the given element container is empty.
	 * @throws UnequalArgumentException if not as many elements are given as the number of rows of this matrix.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<E> addColumn(final E... elements) {
		
		//TODO: Check if all the given elements are not null.
		
		//Checks if the given element container is not empty.
		if (elements.length == 0) {
			throw new EmptyArgumentException(new ArgumentName("elements"));
		}
		
		//Handles the case if this matrix is empty.
		if (isEmpty()) {
			
			this.elements = new Object[elements.length][];
			
			//Iterates the given elements.
			for (int i = 0; i < getColumnCount(); i++) {			
				this.elements[i][0] = elements[i];
			}
		}
		
		//Handles the case if this matrix is not empty.
		else {
			
			//Checks if as many elements are given as the number of rows of this matrix.
			Validator
			.supposeThat(elements.length)
			.thatIsNamed("number of given elements")
			.equals(getRowCount());
			
			//Iterates the rows of this matrix.
			for (int i = 0; i < getRowCount(); i++) {
				
				final Object[] row = new Object[getColumnCount() + 1];
				
				//Iterates the current row.
				for (int j = 0; j < getColumnCount(); j++) {
					row[j] = this.elements[i][j];
				}
				
				row[getColumnCount()] = elements[i];
				
				this.elements[i] = row;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds a new row to this matrix with the given elements.
	 * The complexity of this method is O(n) if this matrix contains n columns.
	 * 
	 * @param elements
	 * @return this matrix.
	 * @throws EmptyArgumentException if the given element container is empty.
	 * @throws UnequalArgumentException if not as many elements are given as the number of columns of this matrix.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<E> addRow(final E... elements) {
		
		//TODO: Check if all the given elements are not null.
		
		//Checks if the given element container is not empty.
		if (elements.length == 0) {
			throw new EmptyArgumentException(new ArgumentName("elements"));
		}
		
		//Handles the case if this matrix is empty.
		if (isEmpty()) {
			
			this.elements = new Object[1][elements.length];
			
			//Iterates the given elements.
			for (int i = 0; i < getColumnCount(); i++) {
				this.elements[0][i] = elements[i];
			}
		}
		
		//Handles the case if this matrix is not empty.
		else {
			
			//Checks if as many elements are given as the number of columns of this matrix.
			Validator
			.supposeThat(elements.length)
			.thatIsNamed("number of given elements")
			.equals(getColumnCount());
			
			final Object[][] newElements = new Object[getRowCount() + 1][getColumnCount()];
			
			for (int i = 0; i < getRowCount(); i++) {
				newElements[i] = this.elements[i];
			}
			
			//Iterates the given elements.
			for (int i = 0; i < getColumnCount(); i++) {				
				newElements[getRowCount()][i] = elements[i];
			}
			
			this.elements = newElements;
		}
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * Removes all elements of this matrix.
	 */
	public void clear() {
		elements = null;
		System.gc();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return true if this matrix contains any element.
	 */
	public boolean containsAny() {
		
		//Calls the default method of the required interface.
		return IContainer.super.containsAny();
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of columns of this matrix.
	 */
	public int getColumnCount() {
		
		//Handles the case if this matrix is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case if this matrix is not empty.
		return elements[0].length;
	}
	
	//method
	/**
	 * The complexity of this method is O(m * n) if:
	 * -This matrix contains m rows.
	 * -This matrix contains n columns.
	 * 
	 * @return a new matrix with the elements of this matrix.
	 */
	public Matrix<E> getCopy() {
		
		final Matrix<E> matrix = new Matrix<E>();
		
		if (!isEmpty()) {
			matrix.elements = elements.clone();
		}
		
		return matrix;
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of elements of this matrix.
	 */
	public int getElementCount() {
		return (getRowCount() * getColumnCount());
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of rows of this matrix.
	 */
	public int getRowCount() {
		
		//Handles the case if this matrix is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case if this matrix is not empty.
		return elements.length;
	}
		
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return true if this matrix is empty.
	 */
	public boolean isEmpty() {
		return (elements == null);
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new iterator of this matrix.
	 */
	public MatrixIterator<E> iterator() {
		return new MatrixIterator<E>(this);
	}
	
	//method
	/**
	 * Sets the given element to this matrix at the row with the given row index and the column with the given column index.
	 * The complexity of this implementation is O(1).
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @param element
	 * @return this matrix.
	 * @throws NonPositiveArgumentException if the given row index is not positive.
	 * @throws NonPositiveArgumentException if the given column index is not positive.
	 * @throws UnexistingPropertyException if this matrix contains no row with the given row index.
	 * @throws UnexistingPropertyException if this matrix contains no column with the given column index.
	 * @throws NullArgumentException if the given element is null.
	 */
	public Matrix<E> set(
		final int rowIndex,
		final int columnIndex,
		final E element
	) {
		//Checks the given parameters.
		Validator.supposeThat(rowIndex).thatIsNamed("row index").isPositive();
		Validator.supposeThat(columnIndex).thatIsNamed("column index").isPositive();
		throwExceptionIfDoesNotContainRow(rowIndex);
		throwExceptionIfDoesNotContainColumn(columnIndex);
		Validator.supposeThat(element).thatIsNamed("element").isNotNull();
		
		elements[rowIndex - 1][columnIndex - 1] = element;
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if:
	 * -This matrix contains n elements.
	 * -The given transformer has a complexity of O(1).
	 * 
	 * @return a new matrix with the elements the given transformer transforms from the elements of this matrix.
	 * @param transformer
	 */
	@SuppressWarnings("unchecked")
	public <O> Matrix<O> toContainer(final IElementTakerElementGetter<E, O> transformer) {
		
		//Creates matrix.
		final Matrix<O> matrix = new Matrix<O>();
		
		//Fills up the elements of the matrix.
			
			matrix.elements = new Object[getRowCount()][getColumnCount()];
			
			//Iterates the rows of this matrix.
			for (int i = 0; i < getRowCount(); i++) {
				
				//Iterates the columns of the current row.
				for (int j = 0; j < getColumnCount(); j++) {
					matrix.elements[i][j] = transformer.getOutput((E)elements[i][j]);
				}
			}
		
		return matrix;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if this matrix contains n elements.
	 * 
	 * @return a string that represents this matrix
	 */
	public String toString() {
		
		String string = StringManager.EMPTY_STRING;
		
		//Iterates the rows of this matrix.
		for (int i = 0; i < getRowCount(); i++) {
			
			String rowString = StringManager.EMPTY_STRING;
			
			//Iterates the columns of the current row.
			for (int j = 0; j < getColumnCount(); j++) {
				rowString += elements[i][j].toString();
				if (j < getColumnCount() - 1) {
					rowString += ',';
				}
			}
			
			if (i < getRowCount() - 1) {
				rowString += ';';
			}
			
			string += rowString;
		}
		
		return string;
	}
	
	//method
	/**
	 * @param columnIndex
	 * @throws UnexistingAttributeException if this matrix contains no column with the given column index.
	 */
	private void throwExceptionIfDoesNotContainColumn(final int columnIndex) {
		if (columnIndex < 1 || columnIndex > getColumnCount()) {
			throw new UnexistingAttributeException(this, "column with the column index " + columnIndex);
		}
	}
	
	//method
	/**
	 * @param rowIndex
	 * @throws UnexistingPropertyException if this matrix contains no row with the given row index.
	 */
	private  void throwExceptionIfDoesNotContainRow(final int rowIndex) {
		if (rowIndex < 1 || rowIndex > getRowCount()) {
			throw new UnexistingAttributeException(this, "row with the row index " + rowIndex);
		}
	}
}
