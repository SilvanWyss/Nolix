//package declaration
package ch.nolix.common.math;

//Java import
import java.util.Random;

import ch.nolix.common.commonTypeHelper.DoubleHelper;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.skillAPI.ApproximativeEqualing;
import ch.nolix.common.validator.Validator;

//class
/**
 * This class represents a matrix of doubles.
 * A matrix has at least 1 row and 1 column.
 * 
 * All comparisons a matrix does internally are approximative. For example:
 * -The second row of the matrix [4, 5; 0.000000001, 0] counts as a zero row.
 * -The matrix [0.9999999999, 0; 0, 1] counts as an identity matrix.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 1140
 */
public class Matrix implements ApproximativeEqualing {
	
	//multi-attribute
	private double[][] values;
	
	//static method
	/**
	 * @param size
	 * @return a new identity matrix with the given size.
	 * @throws NonPositiveArgumentException if the given size is not positive.
	 */
	public static Matrix createIdendityMatrix(final int size) {
		return new Matrix(size).setDiagonalValuesTo(1.0);
	}
	
	//static method
	/**
	 * @param size
	 * @return a new matrix of ones with the given size.
	 * @throws NonPositiveArgumentException if the given size is not positive.
	 */
	public static Matrix createMatrixOfOnes(final int size) {
		return new Matrix(size).setAllValuesTo(1.0);
	}
	
	//static method
	/**
	 * @param rowCount
	 * @param columnCount
	 * @return a new matrix of ones with the given number of rows and the given number of columns.
	 * @throws NonPositiveArgumentException if the given number of rows is not positive.
	 * @throws NonPositiveArgumentException if the given number of columns is not positive.
	 */
	public static Matrix createMatrixOfOnes(final int rowsCount, final int columnsCount) {
		return new Matrix(rowsCount, columnsCount).setAllValuesTo(1.0);
	}
	
	//static method
	/**
	 * @param size
	 * @return a new matrix with the given size.
	 * The values of the matrix are whole random numbers in [0, 99].
	 */
	public static Matrix createRandomMatrix(final int size) {
		
		//Asserts that the given size is positive.
		Validator.assertThat(size).thatIsNamed(VariableNameCatalogue.SIZE).isPositive();
		
		return createRandomMatrix(size, size);
	}
	
	//static method
	/**
	 * @param rowCount
	 * @param columnCount
	 * @return a newly created matrix with the given number of rows and the given number of columns.
	 * The values of the matrix are whole random numbers in [0, 99].
	 * @throws NonPositiveArgumentException if the given number of rows is not positive.
	 * @throws NonPositiveArgumentException if the given number of columns is not positive.
	 */
	public static final Matrix createRandomMatrix(final int rowCount, final int columnCount) {
		
		//Creates matrix.
		final Matrix matrix = new Matrix(rowCount, columnCount);
		
		//Fills up the matrix with random values.
		final var random = new Random();
		
		//Iterates the rows of the matrix.
		for (int i = 0; i < matrix.getRowCount(); i++) {
			
			//Iterates the cells of the current row.
			for (int j = 0; j < matrix.getColumnCount(); j++) {
				matrix.values[i][j] = random.nextInt(100);
			}
		}
		
		return matrix;
	}
	
	//constructor
	/**
	 * Creates a new zero matrix with the given size.
	 * 
	 * @param size
	 * @throws NonPositiveArgumentException if the given size is not positive.
	 */
	public Matrix(final int size) {
		
		//Asserts that the given size is positive.
		Validator.assertThat(size).thatIsNamed(VariableNameCatalogue.SIZE).isPositive();
		
		values = new double[size][size];
	}
	
	//constructor
	/**
	 * Creates a new matrix with the given number of rows and the given number of columns.
	 * 
	 * @param rowCount
	 * @param columnCount
	 * @throws NonPositiveArgumentException if the given row count is not positive.
	 * @throws NonPositiveArgumentException if the given column count is not positive.
	 */
	public Matrix(final int rowCount, final int columnCount) {
		
		//Asserts that the given row count is positive.
		Validator.assertThat(rowCount).thatIsNamed(VariableNameCatalogue.ROW_COUNT).isPositive();
		
		//Asserts that the given row count is positive.
		Validator.assertThat(columnCount).thatIsNamed(VariableNameCatalogue.COLUMN_COUNT).isPositive();
		
		values = new double[rowCount][columnCount];
	}
	
	//constructor
	/**
	 * Creates a new matrix with the given number of rows and columns.
	 * The values of the matrix are all set to the given value.
	 * 
	 * @param rowCount
	 * @param columnCount
	 * @param value
	 * @throws NonPositiveArgumentException if the given row count is not positive.
	 * @throws NonPositiveArgumentException if the given column count is not positive.
	 */
	public Matrix(final int rowsCount, final int columnsCount, final double value) {
		
		//Calls other constructor.
		this(rowsCount, columnsCount);
		
		setAllValuesTo(value);
	}
	
	//method
	/**
	 * Adds the given matrix to this matrix.
	 * 
	 * @param matrix
	 * @return this matrix.
	 * @throws UnequalArgumentException if the given matrix has not as many rows as this matrix.
	 * @throws UnequalArgumentException if the given matrix has not as many columns as this matrix.
	 */
	public Matrix add(final Matrix matrix) {
		
		//Asserts that the given matrix has as many rows as this matrix.
		Validator
		.assertThat(matrix.getRowCount())
		.thatIsNamed("number of rows of the given matrix")
		.isEqualTo(getRowCount());
		
		//Asserts that the given matrix has as many columns as this matrix.
		Validator
		.assertThat(matrix.getColumnCount())
		.thatIsNamed("number of columns of the given matrix")
		.isEqualTo(getColumnCount());
		
		//Iterates the rows of this matrix.
		for (int i = 0; i < getRowCount(); i++) {
			
			//Iterates the cells of the current row.
			for (int j = 0; j < getColumnCount(); j++) {
				values[i][j] += matrix.values[i][j];
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Appends the given matrix at the right of this matrix.
	 * 
	 * @param matrix
	 * @return this matrix.
	 * @throws UnequalArgumentException if the given matrix has not as many rows as this matrix.
	 */
	public Matrix appendAtRight(final Matrix matrix) {
		
		//Asserts that the given matrix has as many rows as this matrix.
		Validator
		.assertThat(matrix.getRowCount())
		.thatIsNamed("number of rows of the given matrix")
		.isEqualTo(getRowCount());
		
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
	 * Appends a new row with the given row values on the bottom of this matrix.
	 * 
	 * @param values
	 * @return this matrix.
	 * @throws InvalidArgumentException
	 * if not as many row values are given than the number of columns of this matrix.
	 */
	public Matrix appendRowAtBottom(double... rowValues) {
		
		//Asserts that as many row values are given than the number of columsn of this matrix.
		Validator.assertThat(rowValues).thatIsNamed("row values").hasElementCount(getColumnCount());
		
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
	@Override
	public boolean equals(Object object) {
				
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
	@Override
	public boolean equalsApproximatively(final Object object, final double epsilon) {
		
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
	public int getColumnCount() {
		return values[0].length;
	}
	
	//method
	/**
	 * @return a clone of this matrix
	 */
	public Matrix getClone() {
		Matrix matrix = new Matrix(1);
		matrix.values = values;
		return matrix;
	}
	
	//method
	/**
	 * @return the column vectors of this matrix
	 */
	public Vector[] getColumnVectors() {
		
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
	 * @return the inverse matrix of this matrix.
	 * @throws InvalidArgumentExceptionException if this matrix is not regular.
	 */
	public Matrix getInverse() {
		
		//Asserts that this matrix is quadratic.
		supposeIsQuadratic();
				
		final Matrix matrix =
		getClone()
		.appendAtRight(Matrix.createIdendityMatrix(getRowCount()))
		.tranformFirstPartToIdentityMatrix();
		
		if (matrix.getRowCount() < getRowCount()) {
			throw new InvalidArgumentException(this, "is not regular");
		}
		
		return matrix.getMatrixWithLastColumns(getColumnCount());
	}
	
	//method
	/**
	 * @param columnCount
	 * @return a matrix with the first columns of this matrix.
	 * @throws OutOfRangeException if the given column count is not valid.
	 */
	public Matrix getMatrixWithFirstColumns(int columnCount) {
		
		//Asserts that the given column count is valid.
		Validator
		.assertThat(columnCount)
		.thatIsNamed(VariableNameCatalogue.COLUMN_COUNT)
		.isBetween(1, getColumnCount());
		
		final Matrix matrix = new Matrix(getRowCount(), columnCount);
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < columnCount; j++) {
				matrix.values[i][j] = values[i][j];
			}
		}
		
		return matrix;
	}
	
	//method
	/**
	 * @param columnCount
	 * @return a matrix with the last columns of this matrix.
	 * @throws OutOfRangeException if the given column count is not valid.
	 */
	public Matrix getMatrixWithLastColumns(final int columnCount) {
		
		//Asserts that the given column count is valid.
		Validator
		.assertThat(columnCount)
		.thatIsNamed(VariableNameCatalogue.COLUMN_COUNT)
		.isBetween(1, getColumnCount());
		
		final Matrix matrix = new Matrix(getRowCount(), columnCount);
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < columnCount; j++) {
				matrix.values[i][j] = values[i][columnCount + j];
			}
		}
		
		return matrix;
	}
	
	//method
	/**
	 * This method implements the least squares algorithm.
	 * 
	 * @param solutionMatrix
	 * @return a matrix A so that the matrix X*A-Y is minimal.
	 * -X is this matrix.
	 * -Y is the given solution matrix.
	 * The following formula is used: A = (X_t*X)^-1*X_t*Y.
	 * @throws UnequalArgumentException if the given solution matrix has not 1 column.
	 * @throws UnequalArgumentException if the given solution matrix has not as many rows as this matrix.
	 */
	public Matrix getMinimalFactorMatrix(final Matrix solutionMatrix) {
		
		//Asserts that the given solution matrix has 1 column.
		Validator
		.assertThat(solutionMatrix.getColumnCount())
		.thatIsNamed("number of columns of the given soluction matrix")
		.isEqualTo(1);
		
		//Asserts that the given solution matrix has as many rows as this matrix.
		Validator
		.assertThat(solutionMatrix.getRowCount())
		.thatIsNamed("number of rows of the given solution matrix")
		.isEqualTo(getRowCount());
		
		final Matrix transposedMatrix = getTransposed();
		final Matrix matrix = transposedMatrix.getProduct(this);
		Matrix inverseMatrix = null;
		
		try {
			inverseMatrix = matrix.getInverse();
		}
		catch (final Exception exception) {
			inverseMatrix = matrix.getPseudoInverse();
		}
				
		return
		inverseMatrix
		.getProduct(transposedMatrix)
		.getProduct(solutionMatrix);
	}
	
	//method
	/**
	 * @param matrix
	 * @return the product of this matrix and the given matrix.
	 * @throws UnequalArgumentException if the given matrix has not as many rows as this matrix columns has.
	 */
	public Matrix getProduct(final Matrix matrix) {
		
		//Asserts that the given matrix has as many rows as the number of columns of this matrix.
		Validator
		.assertThat(matrix.getRowCount())
		.thatIsNamed("number of rows of the given matrix")
		.isEqualTo(getColumnCount());
		
		final Matrix product = new Matrix(getRowCount(), matrix.getColumnCount());
		
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
	 * @return a pseudo inverse matrix of this matrix.
	 * @throws InvalidArgumentException if this matrix is not quadratic.
	 */
	public Matrix getPseudoInverse() {
		
		//Asserts that this matrix is quadratic.
		supposeIsQuadratic();
		
		return
		getSum(new Matrix(getRowCount())
		.setDiagonalValuesTo(0.001))
		.getInverse();
	}
	
	//method
	/**
	 * @return the rank of this matrix.
	 * @throws InvalidArgumentException if this matrix is not quadratic.
	 */
	public int getRank() {
		
		//Asserts that this matrix is quadratic.
		supposeIsQuadratic();
		
		return getClone().transformToEquivalentUpperLeftMatrix().getRowCount();
	}
	
	//method
	/**
	 * @return the number of rows of this matrix
	 */
	public int getRowCount() {
		return values.length;
	}
	
	//method
	/**
	 * @return the row vectors of this matrix
	 */
	public Vector[] getRowVectors() {
		
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
	public int getSize() {
		return (getRowCount() * getColumnCount());
	}
	
	//method
	/**
	 * @return the solution when this matrix is an extended matrix of a linear equation system
	 */
	public double[] getSolutionAsExtendedMatrix() {
		
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
	public Matrix getSum(final Matrix matrix) {
		return getClone().add(matrix);
	}
	
	//method
	/**
	 * @return the transposed matrix of this matrix.
	 */
	public Matrix getTransposed() {
		return getClone().transpose();
	}
	
	//method
	/**
	 * @return the trace of this matrix.
	 * @throws InvalidArgumentException if this matrix is not quadratic.
	 */
	public double getTrace() {
		
		//Asserts that this matrix is quadratic.
		supposeIsQuadratic();
		
		double trace = 0.0;
		for (int i = 0; i < getRowCount(); i++) {
			trace += values[i][i];
		}
		
		return trace;
	}
	
	//method
	/**
	 * @param rowIndex
	 * @param columnIndex
	 * @return the value in the row with the given row index and the column with the given column index.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not contain a row with the given row index.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not contain a column with the given column index.
	 */
	public double getValue(final int rowIndex, final int columnIndex) {
		
		//Asserts that this matrix contains a row with the given row index.
		Validator
		.assertThat(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isBetween(1, getRowCount());
		
		//Asserts that this matrix contains a column with the given column index.
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isBetween(1, getColumnCount());
		
		return values[rowIndex - 1][columnIndex - 1];
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * @param matrix
	 * @return true if this matrix has the same size as the given matrix
	 */
	public boolean hasSameSize(Matrix matrix) {
		return (
			getRowCount() == matrix.getRowCount() &&
			getColumnCount() == matrix.getColumnCount()
		);
	}
	
	//method
	/**
	 * @return true if this matrix is an identity matrix
	 */
	public boolean isIdentityMatrix() {
		
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
	public boolean isQuadratic() {
		return (getRowCount() == getColumnCount());
	}
	
	//method
	/**
	 * @return true if this matrix is regular
	 */
	public boolean isRegular() {
		return (isQuadratic() && getRank() == getRowCount());
	}
	
	//method
	/**
	 * Multiplies this matrix with the given factor.
	 * 
	 * @param factor
	 * @return this matrix
	 */
	public Matrix multiply(double factor) {
		
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				values[i][j] *= factor;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Multiplies the row with the given row index with the given factor.
	 * 
	 * @param rowIndex
	 * @param factor
	 * @return this matrix.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not contain a row with the given row index.
	 */
	public Matrix multiplyRow(final int rowIndex, final double factor) {
		
		//Asserts that this matrix contains a row with the given row index.
		Validator.assertThat(rowIndex).thatIsNamed(VariableNameCatalogue.ROW_INDEX).isBetween(1, getRowCount());
		
		//Iterates the cells of the row with the given row index.
		for (int i = 0; i < getColumnCount(); i++) {
			values[rowIndex][i] *= factor;
		}
		
		return this;
	}
	
	//method
	/**
	 * Removes the zero rows of this matrix using an epsilon
	 * 
	 * @return this matrix
	 */
	public Matrix removeZeroRows() {
		
		LinkedList<double[]> newValues = new LinkedList<>();
		
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
		
		values = new double[newValues.getSize()][values.length];
		for (int i = 0; i < newValues.getSize(); i++) {
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
	public Matrix setAllValuesTo(double value) {
		
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				values[i][j] = value;
			}
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the value in the row with the given row index
	 * and the column with the given column index.
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @param value
	 * @return this matrix.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not contain a row with the given row index.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not contain a column with the given column index.
	 */
	public Matrix setValue(final int rowIndex, final int columnIndex, final double value) {
		
		//Asserts that this matrix contains a row with the given row index.
		Validator
		.assertThat(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isBetween(1, getRowCount());
		
		//Asserts that this matrix contains a column with the given column index.
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isBetween(1, getColumnCount());
		
		values[rowIndex - 1][columnIndex - 1] = value;
		
		return this;
	}
	
	//method
	/**
	 * Sets the values of this matrix.
	 * 
	 * @param values
	 * @return this matrix.
	 * @throws InvalidArgumentException if not as many values are given as this matrix contains.
	 */
	public Matrix setValues(final double... values) {
		
		//Asserts that as many values are given as this matrix contains.
		Validator.assertThat(values).hasElementCount(getColumnCount() * getRowCount());
		
		//Iterates the rows of this matrix.
		for (int i = 0; i < getRowCount(); i++) {
			
			//Iterates the cells of the current row.
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
	public Matrix setDiagonalValuesTo(double value) {
		
		supposeIsQuadratic();
		
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
	 * @return this matrix.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not have a row with the given row1 index.
	 * @throws ArgumentIsOutOfRangeException if this matrix does not have a row with the given row1 index.
	 */
	public Matrix swapRows(int row1Index, int row2Index) {
		
		//Asserts that this matrix has a row with the given row 1 index.
		Validator.assertThat(row1Index).thatIsNamed(VariableNameCatalogue.ROW_INDEX).isBetween(1, getRowCount());
		
		//Asserts that this matrix has a row with the given row 2 index.
		Validator.assertThat(row2Index).thatIsNamed(VariableNameCatalogue.ROW_INDEX).isBetween(1, getRowCount());
		
		final double[] temp = values[row1Index - 1];
		values[row1Index - 1] = values[row2Index - 1];
		values[row2Index - 1] = temp;
		
		return this;
	}
	
	//method
	/**
	 * @return a polynom representation of this matrix
	 * @throws Exception if this matrix does not represent a polynom
	 */
	public Polynom toPolynom() {
		
		//Asserts that the upper left element of this matrix is 0.
		if (values[0][0] == 0) {
			throw new RuntimeException("Matrix does not represent a polynom because its upper left element is 0.");
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
	public Vector toVector() {
				
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
	 * @throws InvalidArgumentException if this matrix has more rows than columns.
	 * @throws InvalidArgumentException if this matrix has linear depending rows.
	 */
	public Matrix tranformFirstPartToIdentityMatrix() {
		
		final int rowCount = getRowCount();
		
		//Asserts that this matrix has not more rows than columns.
		if (getRowCount() > getColumnCount()) {
			throw new InvalidArgumentException(this, "has more rows than columns");
		}
		
		//Transforms this matrix to an equivalent upper left matrix.
		transformToEquivalentUpperLeftMatrix();

		//Asserts that this matrix does not have a linear depending rows.	
		if (rowCount != getRowCount()) {
			throw new InvalidArgumentException(this, "has linear depending rows");
		}
		
		//Iterates the rows of this matrix.
		for (int i = getRowCount() - 1; i >= 0; i--) {
			
			if (Calculator.equalsApproximatively(values[i][i], 0.0)) {
				throw new InvalidArgumentException(this, "has linear depending rows");
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
	public Matrix transformToEquivalentUpperLeftMatrix() {
		
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
	@Override
	public String toString() {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder.append('[');
		
		for (var i = 0; i < getRowCount(); i++) {
			
			for (var j = 0; j < getColumnCount(); j++) {
				
				stringBuilder.append(DoubleHelper.toString(values[i][j]));
				
				if (j < getColumnCount() - 1) {
					stringBuilder.append(',');
				}
			}
			
			if (i < getRowCount() - 1) {
				stringBuilder.append(';');
			}
		}
				
		stringBuilder.append(']');
		
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * Transposes this matrix.
	 * 
	 * @return this matrix
	 */
	public Matrix transpose() {
		
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
	 * @throws InvalidArgumentException if this matrix is not quadratic.
	 */
	private final void supposeIsQuadratic() {
		if (!isQuadratic()) {
			throw new InvalidArgumentException(
				this,
				"is not quadratic"
			);
		}
	}
}
