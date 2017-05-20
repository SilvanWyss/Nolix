//package-declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A matrix is a container that stores its elements in rows and columns.
 * 
 * @author	Silvan Wyss
 * @month 2016-07
 * @lines 270
 * @param <E> - The type of the elements of a matrix.
 */
public class Matrix<E> implements IContainer<E> {

	//attribute
	private Object[][] elements;
	
	//method
	/**
	 * Adds a new row to this matrix with the given elements.
	 * The complexity of this method is O(n + m) if:
	 * -This matrix contains n rows.
	 * -m elements are given.
	 * 
	 * @param elements
	 * @return this matrix.
	 * @throws NullArgumentException if the given element container is null.
	 * @throws EmptyArgumentException if the given element container is empty.
	 * @throws UnequalArgumentException if not as many elements are given as the number of columns the other rows of this matrix have.
	 * @throws NullArgumentException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<E> addRow(final E... elements) {
		
		//Checks if the given element container is not null and not empty.
		//TODO: Add functionality for iterable objects to zeta validator.
		//ZetaValidator.supposeThat(elements).thatIsNamed("element container").isNotEmpty();
		
		//Handles the case if this matrix is empty.
		if (isEmpty()) {
			
			this.elements = new Object[0][elements.length];
			
			//Iterates the given elements.
			for (int i = 0; i < getColumnCount(); i++) {
				
				//Checks if the current element is not null.
				Validator.supposeThat(elements[i]).thatIsNamed("element " + i + 1).isNotNull();
						
				this.elements[0][i] = elements[i];
			}
		}
		
		//Handles the case if this matrix is not empty.
		else {
			
			//Checks if as many elements are given as the number of columns the other rows of this matrix have.
			Validator.supposeThat(elements.length).thatIsNamed("number of given elements").equals(getColumnCount());
			
			final Object[][] newElements = new Object[getRowCount() + 1][getColumnCount()];
			
			for (int i = 0; i < getRowCount(); i++) {
				newElements[i] = this.elements[i];
			}
			
			//Iterates the given elements.
			for (int i = 0; i < getColumnCount(); i++) {
				
				//Checks if the current element is not null.
				Validator.supposeThat(elements[i]).thatIsNamed("element " + i + 1).isNotNull();
				
				newElements[getRowCount()][i] = elements[i];
			}
			
			this.elements = newElements;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes all elements of this matrix.
	 */
	public void clear() {
		elements = null;
		System.gc();
	}
	
	//method
	/**
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
	 * @return the number of elements of this matrix.
	 */
	public int getSize() {
		return (getRowCount() * getColumnCount());
	}
		
	//method
	/**
	 * @return true if this matrix is empty.
	 */
	public boolean isEmpty() {
		return (elements == null);
	}
	
	//method
	/**
	 * @return a new iterator of this matrix.
	 */
	public MatrixIterator<E> iterator() {
		return new MatrixIterator<E>(this);
	}
	
	//method
	/**
	 * Sets the given element to this matrix in the row with the given row number and column with the given column number.
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @param element
	 * @return this matrix.
	 * @throws UnexistingPropertyException if this matrix contains no row with the given row number.
	 * @throws UnexistingPropertyException if this matrix contains no column with the given column number.
	 * @throws NullArgumentException if the given element is null.
	 */
	public Matrix<E> set(
		final int rowNumber,
		final int columnNumber,
		final E element
	) {
		
		//Checks the given parameters.
		throwExceptionIfDoesNotContainRow(rowNumber);
		throwExceptionIfDoesNotContainColumn(columnNumber);
		Validator.supposeThat(element).thatIsNamed("element").isNotNull();
		
		elements[rowNumber - 1][columnNumber - 1] = element;
		
		return this;
	}
	
	//method
	/**
	 * @return a new matrix with the elements the given transformer transforms from the elements of this matrix.
	 * 
	 * @param transformer
	 */
	@SuppressWarnings("unchecked")
	public <O> Matrix<O> toContainer(final IElementTakerElementGetter<E, O> transformer) {
		
		//Creates matrix.
		final Matrix<O> matrix = new Matrix<O>();
		
		//Fills up the elements of the matrix.
		matrix.elements = new Object[getRowCount()][getColumnCount()];
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				matrix.elements[i][j] = transformer.getOutput((E)elements[i][j]);
			}
		}
		
		return matrix;
	}
	
	//method
	/**
	 * @return a string that represents this matrix
	 */
	public String toString() {
		
		String string = StringManager.EMPTY_STRING;
		
		for (int i = 0; i < getRowCount(); i++) {
			
			String rowString = StringManager.EMPTY_STRING;
			
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
	 * @param columnNumber
	 * @throws UnexistingAttributeException if this matrix contains no column with the given column number.
	 */
	private void throwExceptionIfDoesNotContainColumn(final int columnNumber) {
		if (columnNumber < 1 || columnNumber > getColumnCount()) {
			throw new UnexistingAttributeException(this, "column with the column number " + columnNumber);
		}
	}
	
	//method
	/**
	 * @param rowNumber
	 * @throws UnexistingPropertyException if this matrix contains no row with the given row number.
	 */
	private  void throwExceptionIfDoesNotContainRow(final int rowNumber) {
		if (rowNumber < 1 || rowNumber > getRowCount()) {
			throw new UnexistingAttributeException(this, "row with the row number " + rowNumber);
		}
	}
}
