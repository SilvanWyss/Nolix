/*
 * file:	Matrix.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines:	1040
 */

//package declaration
package ch.nolix.core.mathematics;

//Java import
import java.util.Random;

//own imports


import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.helper.DoubleHelper;
import ch.nolix.core.interfaces.ApproximativeEqualing;
import ch.nolix.core.validator.Validator;

//class
/**
 * This class represents a matrix of doubles.
 * A matrix has at least 1 row and 1 column.
 * 
 * All comparisons a matrix does with its values are approximative. For example:
 * -The second row of the matrix [4, 5; 0.000000001, 0] counts as zero row.
 * -The matrix [0.9999999999, 0; 0, 1] counts as identity matrix.
 */
public final class Matrix implements ApproximativeEqualing {
		
	//default value
	public static final int DEFAULT_SIZE = 3;

	//multiple attribute
	private double[][] values;
	
	//static method
	/**
	 * @param size
	 * @return a newly created identity matrix with the given size
	 * @throws Exception if the given size is not positive
	 */
	public static final Matrix createIdendityMatrix(final int size) {
		return new Matrix(size).setDiagonalValuesTo(1.0);
	}
	
	//static method
	/**
	 * @param rowsCount
	 * @param columnsCount
	 * @return a newly created matrix of ones with the given number of rows and the given number of columns
	 * @throws Exception if:
	 * -the given number of rows is not positive
	 * -the given number of columns is not positive
	 */
	public static final Matrix createMatrixOfOnes(final int rowsCount, final int columnsCount) {
		return new Matrix(rowsCount, columnsCount).setAllValuesTo(1.0);
	}
	
	//static method
	/**
	 * @param rowsCount
	 * @param columnsCount
	 * @return a newly created matrix with the given number of rows and the given number of columns.
	 * -The values of the matrix are whole random numbers between and with 1 and 100.
	 */
	public static final Matrix createRandomMatrix(final int rowsCount, final int columnsCount) {
		
		//Creates matrix.
		final Matrix matrix = new Matrix(rowsCount, columnsCount);
		
		//Fills up matrix with random values.
		final Random random = new Random();
		for (int i = 0; i < matrix.getRowCount(); i++) {
			for (int j = 0; j < matrix.getColumnCount(); j++) {
				matrix.values[i][j] = random.nextInt(100) + 1;
			}
		}
		
		return matrix;
	}
	
	//constructor
	/**
	 * Creates new zero matrix with default size.
	 */
	public Matrix() {
		
		//Calls other constructor.
		this(DEFAULT_SIZE);
	}
	
	//constructor
	/**
	 * Creates new zero matrix with the given size.
	 * 
	 * @param size
	 * @throws Exception if the given size is not positive
	 */
	public Matrix(int size) {
		
		Validator.throwExceptionIfValueIsNotPositive("size", size);
				
		values = new double[size][size];
	}
	
	//constructor
	/**
	 * Creates new matrix with the given number of rows and the given number of columns.
	 * 
	 * @param rowsCount
	 * @param columnsCount
	 * @throws Exception if:
	 * -the given number of rows is not positive
	 * -the given number of columns is not positive
	 */
	public Matrix(int rowsCount, int columnsCount) {
		
		Validator.throwExceptionIfValueIsNotPositive("number of rows", rowsCount);
		Validator.throwExceptionIfValueIsNotPositive("number of columns", columnsCount);
		
		values = new double[rowsCount][columnsCount];
	}
	
	//constructor
	/**
	 * Creates new matrix with the given number of rows and columns.
	 * The values of the matrix are all set to the given value.
	 * 
	 * @param rowsCount
	 * @param columnsCount
	 * @param value
	 * @throws Exception if
	 * -the given number of rows is not positive
	 * -the given number of columns is not positive
	 */
	public Matrix(int rowsCount, int columnsCount, double value) {
		
		//Calls other constructor.
		this(rowsCount, columnsCount);
		
		setAllValuesTo(value);
	}
	
	//method
	/**
	 * Adds the given matrix to this matrix.
	 * 
	 * @param matrix
	 * @return this matrix
	 * @throws Exception if the given matrix has not the same size as this matrix
	 */
	public final Matrix add(Matrix matrix) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of rows", getRowCount(), matrix.getRowCount());
		Validator.throwExceptionIfValueIsNotEqual("number of columns", getColumnCount(), matrix.getColumnCount());
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				values[i][j] += matrix.values[i][j];
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Appends the given matrix at the right side of this matrix
	 * 
	 * @param matrix
	 * @return this matrix
	 * @throws Exception if the given matrix has not as many rows as this matrix
	 */
	public final Matrix appendAtRight(Matrix matrix) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of rows", getRowCount(), matrix.getRowCount());
		
		double[][] newValues = new double[getRowCount()][getColumnCount() + matrix.getColumnCount()];
		
		for (int i = 0; i < getRowCount(); i++) {
			
			for (int j = 0; j < getColumnCount(); j++) {
				newValues[i][j] = values[i][j];
			}
			
			for (int j = 0; j < matrix.getColumnCount(); j++) {
				newValues[i][getColumnCount() + j] = matrix.values[i][j];
			}
		}
		
		this.values = newValues;
	
		return this;
	}
	
	//method
	/**
	 * Appends the given row on the bottom of this matrix.
	 * 
	 * @param values
	 * @return this matrix
	 * @throws Exception if not as many values are given than the number of columns of this matrix
	 */
	public final Matrix appendRowAtBottom(double... rowValues) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of columns", getColumnCount(), rowValues.length);
		
		double[][] oldValues = values;
		values = new double[oldValues.length + 1][oldValues[0].length];
		
		for (int i = 0; i < oldValues.length; i++) {
			values[i] = oldValues[i];
		}
		
		for (int i = 0; i < getColumnCount(); i++) {
			values[getRowCount() - 1][i] = rowValues[i];
		}
		
		return this;
	}
	
	//method
	/**
	 * @param object
	 * @return true if this matrix equals the given object
	 */
	public final boolean equals(Object object) {
		
		if (object == null) {
			return false;
		}
		
		if (!(object instanceof Matrix)) {
			return false;
		}
		
		Matrix matrix = (Matrix)object;
		
		if (matrix.getRowCount() != getRowCount()) {
			return false;
		}
		
		if (matrix.getColumnCount() != getColumnCount()) {
			return false;
		}
		
		for (int r = 0; r < getRowCount(); r++) {
			for (int c = 0; c < getColumnCount(); c++) {
				if (matrix.values[r][c] != values[r][c]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * @param object
	 * @param epsilon
	 * @return true if this matrix equals approximatively the given object with a tolerance that is smaller than the given epsilon
	 */
	public final boolean equalsApproximatively(final Object object, final double epsilon) {
		
		if (object == null) {
			return false;
		}
		
		if (!(object instanceof Matrix)) {
			return false;
		}
		
		Matrix matrix = (Matrix)object;
		
		if (matrix.getRowCount() != getRowCount()) {
			return false;
		}
		
		if (matrix.getColumnCount() != getColumnCount()) {
			return false;
		}
		
		for (int r = 0; r < getRowCount(); r++) {
			for (int c = 0; c < getColumnCount(); c++) {
				if (!Calculator.equalsApproximatively(matrix.values[r][c], values[r][c], epsilon)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * @return the number of columns of this matrix
	 */
	public final int getColumnCount() {
		return values[0].length;
	}
	
	//method
	/**
	 * @return a clone of this matrix
	 */
	public final Matrix getClone() {
		Matrix matrix = new Matrix();
		matrix.values = values;
		return matrix;
	}
	
	//method
	/**
	 * @return the column vectors of this matrix
	 */
	public final Vector[] getColumnVectors() {
		
		Vector[] columns = new Vector[getColumnCount()];
		
		for (int j = 0; j < getColumnCount(); j++) {
			
			double[] columnValues = new double[getRowCount()];
			
			for (int i = 0; i < getRowCount(); i++) {
				columnValues[i] = values[i][j];
			}
			
			columns[j] = new Vector(columnValues.length).setValues(columnValues);
		}
		
		return columns;
	}
	
	//method
	/**
	 * @return the inverse matrix of this matrix
	 * @throws Exception if this matrix is not regular
	 */
	public final Matrix getInverse() {
		
		throwExceptionIfNotQuadratic();
		

		
		Matrix matrix = getClone().appendAtRight(Matrix.createIdendityMatrix(getRowCount())).tranformFirstPartToIdentityMatrix();
		
		if (matrix.getRowCount() < getRowCount()) {
			throw new RuntimeException("Matrix is singular.");
		}
		
		return matrix.getMatrixWithLastColumns(getColumnCount());
	}
	
	//method
	/**
	 * @param columnsCount
	 * @return a matrix with the first columns of this matrix
	 */
	public final Matrix getMatrixWithFirstColumns(int columnsCount) {
		
		Validator.throwExceptionIfValueIsNotInRange("columns", 1, getColumnCount(), columnsCount);
		
		Matrix matrix = new Matrix(getRowCount(), columnsCount);
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < columnsCount; j++) {
				matrix.values[i][j] = values[i][j];
			}
		}
		
		return matrix;
	}
	
	//method
	/**
	 * @param columnsCount
	 * @return a matrix with the last columns of this matrix
	 */
	public final Matrix getMatrixWithLastColumns(final int columnsCount) {
		
		Validator.throwExceptionIfValueIsNotInRange("columns", 1, getColumnCount(), columnsCount);
		
		Matrix matrix = new Matrix(getRowCount(), columnsCount);
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < columnsCount; j++) {
				matrix.values[i][j] = values[i][columnsCount + j];
			}
		}
		
		return matrix;
	}
	
	//method
	/**
	 * This method implements the least squares algorithm.
	 * 
	 * @param productMatrix
	 * @return a matrix A so that the matrix X*A-Y is minimal
	 * -X is this matrix
	 * -Y is the given solution matrix
	 * @throws Exception if:
	 * -the given solution matrix has not 1 column
	 * -the given solution matrix has not as many rows as this matrix
	 */
	public final Matrix getMinimalFactorMatrix(final Matrix productMatrix) {
		
		Validator.throwExceptionIfValueIsNotOne("columns count", productMatrix.getColumnCount());
		Validator.throwExceptionIfValueIsNotEqual("number of rows", getRowCount(), productMatrix.getRowCount());
		
		final Matrix transposedMatrix = getTransposed();
		
		Matrix matrix = transposedMatrix.getProduct(this);
		Matrix inverseMatrix = null;
		
		try {
			inverseMatrix = matrix.getInverse();
		}
		catch (Exception e) {
			inverseMatrix = matrix.getPseudoInverse();
		}
				
		return inverseMatrix
			.getProduct(transposedMatrix)
			.getProduct(productMatrix);
	}
	
	//method
	/**
	 * @param matrix
	 * @return the product of this matrix and the given matrix
	 * @throws Exception if the given matrix has not as many rows as this matrix columns has
	 * 
	 * This implementation has a complexity of O(m*n*p) if this matrix has m rows and n columns and the given matrix has n rows p columns.
	 */
	public final Matrix getProduct(Matrix matrix) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of rows", getColumnCount(), matrix.getRowCount());
		
		Matrix product = new Matrix(getRowCount(), matrix.getColumnCount());
		
		for (int i = 0; i < product.getRowCount(); i++) {
			for (int j = 0; j < product.getColumnCount(); j++) {
				for (int k = 0; k < getColumnCount(); k++) {
					product.values[i][j] += values[i][k] * matrix.values[k][j];
				}
			}
		}
		
		return product;
	}
	
	//method
	/**
	 * @return a pseudo inverse matrix of this matrix
	 * @throws Exception if this matrix is not quadratic
	 */
	public final Matrix getPseudoInverse() {
		return getSum(new Matrix(getRowCount())
			.setDiagonalValuesTo(0.001))
			.getInverse();
	}
	
	//method
	/**
	 * @return the rank of this matrix
	 * @throws Exception if this matrix is not quadratic
	 */
	public final int getRank() {
		
		throwExceptionIfNotQuadratic();
		
		return getClone().transformToEquivalentUpperLeftMatrix().getRowCount();
	}
	
	//method
	/**
	 * @return the number of rows of this matrix
	 */
	public final int getRowCount() {
		return values.length;
	}
	
	//method
	/**
	 * @return the row vectors of this matrix
	 */
	public final Vector[] getRowVectors() {
		
		Vector[] rows = new Vector[getRowCount()];
		
		for (int i = 0; i < getRowCount(); i++) {
			rows[i] = new Vector(values[i].length).setValues(values[i]);
		}
		
		return rows;
	}
	
	//method
	/**
	 * @return the size of this matrix
	 */
	public final int getSize() {
		return (getRowCount() * getColumnCount());
	}
	
	//method
	/**
	 * @return the solution when this matrix is an extended matrix of a linear equation system
	 */
	public final double[] getSolutionAsExtendedMatrix() {
		
		final double[] solution = new double[getRowCount()];
		
		final Matrix matrix = getClone().transformToEquivalentUpperLeftMatrix();
		
		if (matrix.getRowCount() != getRowCount()) {
			throw new RuntimeException("Coefficient matrix of this extended matrix is not regular.");
		}
		
		for (int i = matrix.getRowCount() - 1; i >= 0; i--) {
		
			double sum = matrix.values[i][matrix.getColumnCount() - 1];
			
			for (int j = matrix.getColumnCount() - 2; j > i; j--) {
				sum -= matrix.values[i][j] * solution[j];
			}
			
			solution[i] = sum / matrix.values[i][i];
		}
		
		return solution;
	}
	
	//method
	/**
	 * @param matrix
	 * @return the matrix that is the sum of this matrix and the given matrix
	 * @throws Exception if the given matrix has not the same size as this matrix
	 */
	public final Matrix getSum(final Matrix matrix) {
		return getClone().add(matrix);
	}
	
	//method
	/**
	 * @return the transposed matrix of this matrix
	 */
	public final Matrix getTransposed() {
		return getClone().transpose();
	}
	
	//method
	/**
	 * @return the trace of this matrix
	 * @throws Exception if this matrix is not quadratic
	 */
	public final double getTrace() {
		
		throwExceptionIfNotQuadratic();
		
		double trace = 0.0;
		
		for (int i = 0; i < getRowCount(); i++) {
			trace += values[i][i];
		}
		
		return trace;
	}
	
	//method
	/**
	 * @param rowNumber
	 * @param columnNumber
	 * @return the value of this matrix in the row with the given row number and the column with the given column number
	 * @throws Exception if:
	 * -this matrix has no row with the given row number
	 * -this matrix has no column with the given column number
	 */
	public final double getValue(int rowNumber, int columnNumber) {
		
		//Check parameters.
		Validator.throwExceptionIfValueIsNotInRange("row number", 1, getRowCount(), rowNumber);
		Validator.throwExceptionIfValueIsNotInRange("column number", 1, getColumnCount(), columnNumber);
		
		return values[rowNumber - 1][columnNumber - 1];
	}
	
	//method
	/**
	 * @param matrix
	 * @return true if this matrix has the same size as the given matrix
	 */
	public final boolean hasSameSize(Matrix matrix) {
		return (
			getRowCount() == matrix.getRowCount() &&
			getColumnCount() == matrix.getColumnCount()
		);
	}
	
	//method
	/**
	 * @return true if this matrix is an identity matrix
	 */
	public final boolean isIdentityMatrix() {
		
		if (!isQuadratic()) {
			return false;
		}
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				if (i != j) {
					if (!Calculator.isApproximatelyZero(values[i][j])) {
						return false;
					}
				}
				else {
					if (!Calculator.isApproximatelyOne(values[i][j])) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * @return true if this matrix is a quadratic matrix
	 */
	public final boolean isQuadratic() {
		return (getRowCount() == getColumnCount());
	}
	
	//method
	/**
	 * @return true if this matrix is regular
	 */
	public final boolean isRegular() {
		return (isQuadratic() && getRank() == getRowCount());
	}
	
	//method
	/**
	 * Multiplies this matrix with the given factor.
	 * 
	 * @param factor
	 * @return this matrix
	 */
	public final Matrix multiply(double factor) {
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				values[i][j] *= factor;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Multiplies the row with the given row number with the given factor.
	 * 
	 * @param rowNumber
	 * @param factor
	 * @return this matrix
	 * @throws Exception if this matrix contains no row with the given row number
	 */
	public final Matrix multiplyRow(int rowNumber, double factor) {
		
		Validator.throwExceptionIfValueIsNotInRange("row number", 1, getRowCount(), rowNumber);
		
		for (int j = 0; j < getColumnCount(); j++) {
			values[rowNumber][j] *= factor;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the zero rows of this matrix using an epsilon
	 * 
	 * @return this matrix
	 */
	public final Matrix removeZeroRows() {
		
		List<double[]> newValues = new List<double[]>();
		
		for (double[] r: values) {
			
			boolean isZeroRow = true;
			
			for (double v: r) {
				if (!Calculator.equalsApproximatively(v, 0.0)) {
					isZeroRow = false;
					break;
				}
			}
			
			if (!isZeroRow) {
				newValues.addAtEnd(r);
			}
		}
		
		values = new double[newValues.getElementCount()][values.length];
		for (int i = 0; i < newValues.getElementCount(); i++) {
			values[i] = newValues.getRefAt(i + 1);
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the values of this matrix to the given value.
	 * 
	 * @param value
	 * @return this matrix
	 */
	public final Matrix setAllValuesTo(double value) {
		
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				values[i][j] = value;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the value in the row with the given row number and the column with the given column number to the given value.
	 * @param rowNumber
	 * @param columnNumber
	 * @param value
	 * @return this matrix
	 * @throws Exception if:
	 * -this matrix has no row with the given row number
	 * -this matrix has no column with the given column number
	 */
	public final Matrix setValue(int rowNumber, int columnNumber, double value) {
		
		//Check parameters.
		Validator.throwExceptionIfValueIsNotInRange("row number", 1, getRowCount(), rowNumber);
		Validator.throwExceptionIfValueIsNotInRange("column number", 1, getColumnCount(), columnNumber);
		
		values[rowNumber - 1][columnNumber - 1] = value;
		
		return this;
	}
	
	//method
	/**
	 * Sets the values of this matrix.
	 * 
	 * @param values
	 * @return this matrix
	 * @throws Exception if not as many values are given as this matrix has
	 */
	public final Matrix setValues(double... values) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of values", getColumnCount() * getRowCount(), values.length);
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				this.values[i][j] = values[i * getColumnCount() + j];
			}
		}
		
		return this;
	}

	//method
	/**
	 * Sets the diagonal values of this matrix to the given value.
	 * 
	 * @param value
	 * @return this matrix
	 * @throws Exception if this matrix is not quadratic
	 */
	public final Matrix setDiagonalValuesTo(double value) {
		
		throwExceptionIfNotQuadratic();
		
		for (int i = 0; i < values.length; i++) {
			values[i][i] = value;
		}
		
		return this;
	}
	
	//method
	/**
	 * Swaps the rows with the given indexes.
	 * 
	 * @param row1Index
	 * @param row2Index
	 * @return this matrix
	 * @throws Exception if:
	 *  -this matrix has no row with the given row1 index
	 *  -this matrix has no row with the givne row2 index
	 */
	public final Matrix swapRows(int row1Index, int row2Index) {
		
		Validator.throwExceptionIfValueIsNotInRange("row 1 index", 1, getRowCount(), row1Index);
		Validator.throwExceptionIfValueIsNotInRange("row 1 index", 1, getRowCount(), row2Index);
		
		double[] temp = values[row1Index - 1];
		values[row1Index - 1] = values[row2Index - 1];
		values[row2Index - 1] = temp;
		
		return this;
	}
	
	//method
	/**
	 * @return a polynom representation of this matrix
	 * @throws Exception if this matrix does not represent a polynom
	 */
	public final Polynom toPolynom() {
		
		//Checks if the upper left element of this matrix is 0.
		if (values[0][0] == 0) {
			throw new RuntimeException("Matrix represents no polynom because its upper left element is 0.");
		}
		
		//Handles the case that this matrix consists of 1 row.
		if (getRowCount() == 1) {
			return new Polynom(getColumnCount() - 1).setCoefficients(values[0]);
		}
		
		//Handles the case that this matrix consists of 1 column.
		if (getColumnCount() == 1) {
			
			final double[] values = new double[getRowCount()];
			
			for (int i = 0; i < getRowCount(); i++) {
				values[i] = this.values[i][0];
			}
			
			return new Polynom(getRowCount() - 1).setCoefficients(values);
		}
		
		//Handles the case that this matrix does not consist of 1 row nor of 1 column.
		throw new RuntimeException("Matrix contains not exactly 1 row or exactly 1 column");
	}
	
	//method
	/**
	 * @return a vector representation of this matrix
	 * @throws Exception if this matrix does not represent a vector
	 */
	public final Vector toVector() {
				
		//Handles the case that this matrix contains 1 row.
		if (getRowCount() == 1) {
			return new Vector(getColumnCount()).setValues(values[0]);
		}
		
		//Handles the case that this matrix contains 1 column.
		if (getColumnCount() == 1) {
			
			final double[] values = new double[getRowCount()];
			
			for (int i = 0; i < getRowCount(); i++) {
				values[i] = this.values[i][0];
			}
			
			return new Vector(getRowCount()).setValues(values);
		}
		
		//Handles the case that this matrix does not either contain 1 row nor 1 column.
		throw new RuntimeException("Matrix does not either contain 1 row nor 1 column.");
	}
	
	//method
	/**
	 * Transforms the first part of this matrix to an identity matrix.
	 * 
	 * @return this matrix
	 * @throws Exception if:
	 * -this matrix has more rows than columns
	 * -this matrix has linear dependent rows
	 */
	public final Matrix tranformFirstPartToIdentityMatrix() {
		
		final int rowsCount = getRowCount();
		
		Validator.throwExceptionIfValueIsBigger("number of rows", getColumnCount(), rowsCount);
		
		//Transforms this matrix to an equivalent upper left matrix.
		transformToEquivalentUpperLeftMatrix();
		
		Validator.throwExceptionIfValueIsNotEqual("number of rows", rowsCount, getRowCount());
		
		for (int i = getRowCount() - 1; i >= 0; i--) {
			
			if (Calculator.equalsApproximatively(values[i][i], 0.0)) {
				throw new RuntimeException("Matrix had linear dependent rows.");
			}
			
			final double factor = 1 / values[i][i];
			for (int j = 0; j < getColumnCount(); j++) {
				values[i][j] *= factor;
			}
						
			for (int j = i + 1; j < getRowCount(); j++) {
				
				double factor2 = values[i][j];
				
				for (int k = 0; k < getColumnCount(); k++) {
					values[i][k] -= factor2 * values[j][k];
				}
				
			}
		}

		return this;
	}
	
	//method
	/**
	 * Transforms this matrix to an equivalent upper left matrix.
	 * 
	 * @return this matrix
	 */
	public final Matrix transformToEquivalentUpperLeftMatrix() {
		
		int minColumnIndex = 0;
		
		//Iterate over rows of this matrix.
		for (int i = 0; i < values.length; i++) {
			
			boolean found = false;
			
			while (!found && minColumnIndex < values[i].length) {
				
				//Iterates over the current row and the rows below the current row.
				for (int j = i; j < values.length; j++) {
					if (values[j][minColumnIndex] != 0) {
						found = true;
						swapRows(i + 1, j + 1);
						break;
					}
				}
				
				if (!found) {
					minColumnIndex++;
				}
			}
			
			if (found) {
				
				//Iterates the rows under the current row.
				for (int j = i + 1; j < values.length; j++) {
										
					double factor = -values[j][minColumnIndex] / values[i][minColumnIndex];
					
					//Iterates the values of the row.
					for (int k = minColumnIndex; k < values[j].length; k++) {		
						values[j][k] += factor * values[i][k];
					}
				}
			}
			else {
				break;
			}
			
			//Updates min column index.
			minColumnIndex++;
		}
		
		return removeZeroRows();
	}
	
	//method
	/**
	 * @return a string representation of this object
	 */
	public final String toString() {
		
		String matrix = "[";
		
		for (int i = 0; i < getRowCount(); i++) {	
			
			String line = StringCatalogue.EMPTY_STRING;
			
			for (int j = 0; j < getColumnCount(); j++) {
				
				line +=  DoubleHelper.toString(values[i][j]);
				
				if (j < getColumnCount() - 1) {
					line += ",";
				}
			}
			
			if (i < getRowCount() - 1) {
				line += ";";
			}
			
			matrix += line;
		}
		
		matrix += "]";
		
		return matrix;
	}
	
	//method
	/**
	 * Transposes this matrix.
	 * 
	 * @return this matrix
	 */
	public final Matrix transpose() {
		
		double[][] values = new double[getColumnCount()][getRowCount()];
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				values[j][i] = this.values[i][j];
			}
		}
		
		this.values = values;
		
		return this;
	}
	
	//method
	/**
	 * @throws Exception if this matrix is not quadratic
	 */
	private final void throwExceptionIfNotQuadratic() {
		if (!isQuadratic()) {
			throw new RuntimeException("Matrix with " + getRowCount() + " rows and " + getColumnCount() + " columns is not quadratic.");
		}
	}
}
