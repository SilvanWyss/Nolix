/*
 * file:	Matrix.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	230
 */

//package-declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.util.Validator;

//class
/**
 * A matrix is a container that stores its elements like a table.
 */
public final class Matrix<E> implements IContainer<E> {

	//attribute
	private Object[][] elements;
	
	//method
	/**
	 * Adds new row to this matrix with the given elements.
	 * 
	 * @param elements
	 * @return this matrix
	 * @throws Exception if:
	 * -Not as many elements are given as many columns this matrix contains.
	 * -One of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public final Matrix<E> addRow(E... elements) {
		
		if (isEmpty()) {
			
			this.elements = new Object[1][elements.length];
			
			for (int i = 0; i < getColumnCount(); i++) {
				
				Validator.throwExceptionIfValueIsNull("element " + i , elements[i]);
				
				this.elements[0][i] = elements[i];
			}
		}
		else {
			if (elements.length != getColumnCount()) {
				throw new RuntimeException("There are not given as many elements as many columns this matrix contains.");
			}
			
			Object[][] newElements = new Object[getRowCount() + 1][getColumnCount()];
			
			for (int i = 0; i < getRowCount(); i++) {
				newElements[i] = this.elements[i];
			}
			
			for (int i = 0; i < getColumnCount(); i++) {
				
				Validator.throwExceptionIfValueIsNull("element " + i , elements[i]);
				
				newElements[getRowCount()][i] = elements[i];
			}
			
			this.elements = newElements;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes all rows of this matrix.
	 */
	public final Matrix<E> clear() {
		
		elements = null;
		
		return this;
	}
	
	//method
	/**
	 * @return a new matrix containing the elements of this matrix
	 */
	public final Matrix<E> getCopy() {
		
		final Matrix<E> matrix = new Matrix<E>();
		
		matrix.elements = elements.clone();
		
		return matrix;
	}
	
	//method
	/**
	 * @return the number of elements of this matrix
	 */
	public final int getSize() {
		return (getRowCount() * getColumnCount());
	}
	
	//method
	/**
	 * @param index
	 * @return the element of this matrix with the given index
	 * @throws Exception if this matrix contains no element with the given index
	 */
	@SuppressWarnings("unchecked")
	public final E getRefAt(final int index) {
		
		if (index < 1 || index > getSize()) {
			throw new RuntimeException("Matrix contains no element with the given index.");
		}
		
		return (E)elements[(index / getColumnCount()) - 1][(index % getColumnCount()) - 1];
	}
	
	//method
	/**
	 * @return the number of rows of this matrix
	 */
	public final int getRowCount() {
		return elements.length;
	}
	
	//method
	/**
	 * @return the number of columns of this matrix
	 */
	public final int getColumnCount() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return elements[0].length;
	}
	
	public boolean isEmpty() {
		return elements == null;
	}
	
	//method
	/**
	 * @return an iterator for this matrix
	 */
	public final MatrixIterator<E> iterator() {
		return new MatrixIterator<E>(this);
	}
	
	public final Matrix<E> setAt(int rowNumber, int columnNumber, E element) {
		
		//Checks parameters.
		throwExceptionIfDoesNotContainRow(rowNumber);
		throwExceptionIfDoesNotContainColumn(columnNumber);
		Validator.throwExceptionIfValueIsNull("element", element);
		
		elements[rowNumber - 1][columnNumber - 1] = element;
		
		return this;
	}
	
	//method
	/**
	 * @return a matrix containing the element the given transformer transforms from the elements of this matrix
	 */
	@SuppressWarnings("unchecked")
	public final <O> Matrix<O> toContainer(final IElementTakerElementGetter<E, O> transformer) {
		
		final Matrix<O> matrix = new Matrix<O>();
		
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
	public final String toString() {
		
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
	 * @throws Exception if this matrix contains no column with the given column number
	 */
	private final void throwExceptionIfDoesNotContainColumn(final int columnNumber) {
		if (columnNumber < 1 || columnNumber > getColumnCount()) {
			throw new UnexistingAttributeException(this, "column with the column number " + columnNumber);
		}
	}
	
	//method
	/**
	 * @param rowNumber
	 * @throws Exception if this matrix contains no row with the given row number
	 */
	private final void throwExceptionIfDoesNotContainRow(final int rowNumber) {
		if (rowNumber < 1 || rowNumber > getRowCount()) {
			throw new UnexistingAttributeException(this, "row with the row number " + rowNumber);
		}
	}
}
