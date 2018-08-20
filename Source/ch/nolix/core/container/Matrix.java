//package-declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A {@link Matrix} is a {@link IContainer}
 * that stores its elements in rows and columns.
 * 
 * A {@link Matrix} is clearable.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 650
 * @param <E> The type of the elements of a {@link Matrix}.
 */
public final class Matrix<E> implements IContainer<E>, Clearable<Matrix<E>> {

	//multi-attribute
	private Object[][] elements = new Object[0][0];
	
	//method
	/**
	 * Adds a new column to the current {@link Matrix} with the given elements.
	 * The complexity of this method is O(m + n) if:
	 * -The current {@link Matrix} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link Matrix}.
	 * @throws NullArgumentException if the given elements is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 * @throws UnequalArgumentException
	 * if the current {@link Matrix} is not empty
	 * and if not as many elements are given as the number of rows of the current {@link Matrix}.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<E> addColumn(final E... elements) {
		
		//Calls other method.
		return addColumn(new ReadContainer<E>(elements));
	}
	
	//method
	/**
	 * Adds a new column to the current {@link Matrix} with the given elements.
	 * The complexity of this method is O(m + n) if:
	 * -The current {@link Matrix} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link Matrix}.
	 * @throws NullArgumentException if the given elements is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 * @throws UnequalArgumentException
	 * if the current {@link Matrix} is not empty
	 * and if not as many elements are given as the number of rows of the current {@link Matrix}.
	 */
	public Matrix<E> addColumn(final Iterable<E> elements) {
		
		//Checks if the given elements are not null.
		Validator.supposeTheElements(elements).areNotNull();		
		
		final var elements_ = new ReadContainer<E>(elements);
		
		//Handles the case that the current {@link Matrix} is empty.
		if (isEmpty()) {
			if (elements_.containsAny()) {
				
				this.elements = new Object[elements_.getElementCount()][1];
				
				//Iterates the given elements.
				var i = 0;
				for (final var e : elements_) {
					
					this.elements[i][0] = e;
					
					i++;
				}
			}
		}
		
		//Handles the case that the current matrix is not empty.
		else {
			
			//Checks if as many elements are given as the number of rows of the current matrix.
			Validator
			.suppose(elements_.getElementCount())
			.thatIsNamed("number of the given elements")
			.isEqualTo(getRowCount());
			
			final var columnCount = getColumnCount();
			
			//Iterates the given elements.
			var i = 0;
			for (final var e : elements_) {
				
				final var row = new Object[columnCount + 1];
				
				//Iterates the current row.
				row[columnCount] = e;
				for (var j = 0; j < columnCount; j++) {
					row[j] = this.elements[i][j];
				}
				
				this.elements[i] = row;
				
				i++;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds a new row to the current {@link Matrix} with the given elements.
	 * The complexity of this method is O(m + n) if:
	 * -The current {@link Matrix} contains m rows.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link Matrix}.
	 * @throws NullArgumentException if the given elements is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 * @throws UnequalArgumentException
	 * the current {@link Matrix} is not empty
	 * and if not as many elements are given as the number of columns of the current {@link Matrix}.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<E> addRow(final E... elements) {
		
		//Calls other method.
		return addRow(new ReadContainer<E>(elements));
	}
	
	//method
	/**
	 * Adds a new row to the current {@link Matrix} with the given elements.
	 * The complexity of this method is O(m + n) if:
	 * -The current {@link Matrix} contains m rows.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return the current {@link Matrix}.
	 * @throws NullArgumentException if the given elements is null.
	 * @throws NullArgumentException if one of the given elements is null.
	 * @throws UnequalArgumentException
	 * the current {@link Matrix} is not empty
	 * and if not as many elements are given as the number of columns of the current {@link Matrix}.
	 */
	public Matrix<E> addRow(final Iterable<E> elements) {
		
		//Checks if the given elements are not null.
		Validator.supposeTheElements(elements).areNotNull();
		
		final var elements_ = new ReadContainer<E>(elements);
		
		//Handles the case that the current matrix is empty.
		if (isEmpty()) {
			if (elements_.containsAny()) {
				
				this.elements = new Object[1][elements_.getElementCount()];
				
				//Iterates the given elements.
				var i = 0;
				for (final var e : elements_) {
					
					this.elements[0][i] = e;
					
					i++;
				}
			}
		}
		
		//Handles the case that the current matrix is not empty.
		else {
			
			//Checks if as many elements are given as the number of columns of the current matrix.
			Validator
			.suppose(elements_.getElementCount())
			.thatIsNamed("number of the given elements")
			.isEqualTo(getColumnCount());
			
			final var rowCount = getRowCount();
			final var columnCount = getColumnCount();			
			final var newElements = new Object[rowCount + 1][columnCount];
			
			//Iterates the rows of the current matrix.
			for (var i = 0; i < rowCount; i++) {
				newElements[i] = this.elements[i];
			}
			
			//Iterates the given elements.
			var i = 0;
			for (final var e : elements_) {
				
				newElements[rowCount][i] = e;
				
				i++;
			}
			
			this.elements = newElements;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes all elements of the current {@link Matrix}.
	 * The complexity of this method is O(1).
	 * 
	 * @return the current {@link Matrix}.
	 */
	public Matrix<E> clear() {
		
		elements = new Object[0][0];
		System.gc();
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return true if the current {@link Matrix} contains any element.
	 */
	public boolean containsAny() {
		
		//Calls the default method of the suitable interface.
		return IContainer.super.containsAny();
	}
	
	//method
	/**
	 * @param columnIndex
	 * @return the column of the current {@link Matrix} with the given column index.
	 * @throws NonPositiveArgumentException if the given column index is not positive.
	 * @throws BiggerArgumentException
	 * if the given column index is bigger than the number of columns of the current {@link Matrix}.
	 */
	public MatrixColumn<E> getColumn(final int columnIndex) {
		return new MatrixColumn<E>(this, columnIndex);
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of columns of the current {@link Matrix}.
	 */
	public int getColumnCount() {
		
		//Handles the case that the current {@link Matrix} is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current {@link Matrix} is not empty.
		return elements[0].length;
	}
	
	//method
	/**
	 * @param index
	 * @return the index of the column of the element from the current {@link Matrix} at the given index.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws BiggerArgumentException
	 * if the given index is bigger than the number of elements of the current {@link Matrix}.
	 */
	public int getColumnIndexOf(final int index) {
		
		//Checks if the current matrix contains an element at the given index.
		supposeContainsElementAt(index);
		
		return ((index - 1) % getColumnCount() + 1);
	}
	
	//method
	/**
	 * @return the columns of the {@link Matrix}.
	 */
	public List<MatrixColumn<E>> getColumns() {
		
		final var columns = new List<MatrixColumn<E>>();
		
		//Iterates the columns of the current matrix.
		for (var i = 1; i <= getColumnCount(); i++) {
			columns.addAtEnd(new MatrixColumn<E>(this, i));
		}
		
		return columns;
	}
	
	//method
	/**
	 * The complexity of this method is O(m * n) if:
	 * -This matrix contains m rows.
	 * -This matrix contains n columns.
	 * 
	 * @return a new matrix with the elements of the current {@link Matrix}.
	 */
	public Matrix<E> getCopy() {
		
		final var matrix = new Matrix<E>();
		
		//Handles the case that the current matrix is not empty.
		if (containsAny()) {
			matrix.elements = elements.clone();
		}
		
		return matrix;
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of elements of the current {@link Matrix}.
	 */
	public int getElementCount() {
		return (getRowCount() * getColumnCount());
	}
	
	//method
	/**
	 * @param rowIndex
	 * @param columnIndex
	 * @return the index of the element with the given row index and the given column index.
	 * @throws NonPositiveArgumentException if the given row index is not positive.
	 * @throws BiggerArgumentException
	 * if the given row index is bigger than the number of rows of the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given column index is not positive.
	 * @throws BiggerArgumentException
	 * if the given column index is bigger than the number of columns of the current {@link Matrix}.
	 */
	public int getIndexOf(final int rowIndex, final int columnIndex) {
		
		//Checks if the current matrix contains an element
		//at the given row index and the given column index.
		supposeContainsElementAt(rowIndex, columnIndex);
		
		return ((rowIndex - 1) * getColumnCount() + columnIndex);
	}
	
	//method
	/**
	 * @return the element at the given index from the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws BiggerArgumentException
	 * if the given index is bigger than the number of elements of the current {@link Matrix}.
	 */
	public E getRefAt(final int index) {
		return getRefAt(getRowIndexOf(index), getColumnIndexOf(index));
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @return the element at the given row index and column index from the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given row index is not positive.
	 * @throws BiggerArgumentException
	 * if the given row index is bigger than the number of rows of the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given column index is not positive.
	 * @throws BiggerArgumentException
	 * if the given column index is bigger than the number of columns of the current {@link Matrix}.
	 */
	@SuppressWarnings("unchecked")
	public E getRefAt(final int rowIndex, final int columnIndex) {
		
		//Checks if the current matrix contains an element at the given row index and column index.
		supposeContainsElementAt(rowIndex, columnIndex);
		
		return (E)elements[rowIndex - 1][columnIndex - 1];
	}
	
	//method
	/**
	 * @param rowIndex
	 * @return the row of the current {@link Matrix} with the given row index.
	 * @throws NonPositiveArgumentException if the given row index is not positive.
	 * @throws BiggerArgumentException
	 * if the given row index is bigger than the number of rows of the current {@link Matrix}.
	 */
	public MatrixRow<E> getRow(final int rowIndex) {
		return new MatrixRow<E>(this, rowIndex);
	}
	
	//method
	/**
	 * @param index
	 * @return the index of the row of the element from the current {@link Matrix} at the given index.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws BiggerArgumentException
	 * if the given index is bigger than the number of elements of the current {@link Matrix}.
	 */
	public int getRowIndexOf(final int index) {
		
		//Checks if the current matrix contains an element at the given index.
		supposeContainsElementAt(index);
		
		return ((index - 1) / getColumnCount() + 1);
	}
	
	//method
	/**
	 * @return the rows of the current {@link Matrix}.
	 */
	public List<MatrixRow<E>> getRows() {
		
		final var rows = new List<MatrixRow<E>>();
		
		//Iterates the rows of the current matrix.
		for (var i = 1; i <= getRowCount(); i++) {
			rows.addAtEnd(new MatrixRow<E>(this, i));
		}
		
		return rows;
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return the number of rows of the current {@link Matrix}.
	 */
	public int getRowCount() {
		
		//Handles the case that the current {@link Matrix} is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current {@link Matrix} is not empty.
		return elements.length;
	}
		
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return true if the current {@link Matrix} is empty.
	 */
	public boolean isEmpty() {
		return (elements.length == 0);
	}
	
	//method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new iterator for the current {@link Matrix}.
	 */
	public MatrixIterator<E> iterator() {
		return new MatrixIterator<E>(this);
	}
	
	//method
	/**
	 * Sets the given element to the current {@link Matrix} at the given index.
	 * The complexity of this implementation is O(1).
	 * 
	 * @param index
	 * @param element
	 * @return the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws BiggerArgumentException
	 * if the given index is bigger than the number of elements of the current {@link Matrix}.
	 * @throws NullArgumentException if the given element is null.
	 */
	public Matrix<E> set(final int index, final E element) {
				
		//Checks if the given element is not null.
		Validator
		.suppose(element)
		.thatIsNamed(VariableNameCatalogue.ELEMENT)
		.isNotNull();
		
		//Sets the given element at the given index to the current matrix.
		elements[getRowIndexOf(index)][getColumnIndexOf(index)] = element;
				
		return this;
	}
	
	//method
	/**
	 * Sets the given element to the current {@link Matrix}
	 * to the row with the given row index and the column with the given column index.
	 * 
	 * The complexity of this implementation is O(1).
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @param element
	 * @return the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given row index is not positive.
	 * @throws BiggerArgumentException
	 * if the given row index is bigger than the number of rows of the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given column index is not positive.
	 * @throws BiggerArgumentException
	 * if the given column index is bigger than the number of columns of the current {@link Matrix}.
	 * @throws NullArgumentException if the given element is null.
	 */
	public Matrix<E> set(
		final int rowIndex,
		final int columnIndex,
		final E element
	) {
		
		//Checks if the current matrix contains an element at the given row index and column index.
		supposeContainsElementAt(rowIndex, columnIndex);
		
		//Checks if the given element is not null.
		Validator
		.suppose(element)
		.thatIsNamed(VariableNameCatalogue.ELEMENT)
		.isNotNull();
		
		elements[rowIndex - 1][columnIndex - 1] = element;
		
		return this;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if:
	 * -This matrix contains n elements.
	 * -The given transformer has a complexity of O(1).
	 * 
	 * @return a new matrix with the elements
	 * the given transformer transforms from the elements of the current {@link Matrix}.
	 * @param transformer
	 */
	@SuppressWarnings("unchecked")
	public <O> Matrix<O> toMatrix(final IElementTakerElementGetter<E, O> transformer) {
		
		//Creates matrix.
		final var matrix = new Matrix<O>();
		
		//Fills up the elements of the matrix.
			
			matrix.elements = new Object[getRowCount()][getColumnCount()];
			
			//Iterates the rows of the current {@link Matrix}.
			for (var i = 0; i < getRowCount(); i++) {
				
				//Iterates the columns of the current row.
				for (var j = 0; j < getColumnCount(); j++) {
					matrix.elements[i][j] = transformer.getOutput((E)elements[i][j]);
				}
			}
		
		return matrix;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Matrix} contains n elements.
	 * 
	 * @return a string representation of the current {@link Matrix}.
	 */
	public String toString() {
		
		final var stringBuilder = new StringBuilder();
		
        //Iterates the rows of the current matrix.
        for (var i = 0; i < getRowCount(); i++) {
            
        	if (i > 0) {
        		stringBuilder.append(CharacterCatalogue.SEMICOLON);
        	}
        	
            //Iterates the columns of the current row.
            for (int j = 0; j < getColumnCount(); j++) {
            	
            	if (j > 0) {
            		stringBuilder.append(CharacterCatalogue.COMMA);
            	}
            	
                stringBuilder.append(elements[i][j].toString());
            }
        }
        
        return stringBuilder.toString();
	}
	
	//method
	/**
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws BiggerArgumentException
	 * if the given index is bigger than the number of elements of the current {@link Matrix}.
	 */
	private void supposeContainsElementAt(final int index) {
		
		Validator
		.suppose(index)
		.thatIsNamed(VariableNameCatalogue.INDEX)
		.isPositive();
		
		Validator
		.suppose(index)
		.thatIsNamed(VariableNameCatalogue.INDEX)
		.isNotBiggerThan(getElementCount());
	}
	
	//method
	/**
	 * @param rowIndex
	 * @param columnIndex
	 * @throws NonPositiveArgumentException if the given row index is not positive.
	 * @throws BiggerArgumentException
	 * if the given row index is bigger than the number of rows of the current {@link Matrix}.
	 * @throws NonPositiveArgumentException if the given column index is not positive.
	 * @throws BiggerArgumentException
	 * if the given column index is bigger than the number of columns of the current {@link Matrix}.
	 */
	private void supposeContainsElementAt(final int rowIndex, final int columnIndex) {
		
		Validator
		.suppose(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		Validator
		.suppose(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isNotBiggerThan(getRowCount());
		
		Validator
		.suppose(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isPositive();
		
		Validator
		.suppose(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isNotBiggerThan(getColumnCount());
	}
}
