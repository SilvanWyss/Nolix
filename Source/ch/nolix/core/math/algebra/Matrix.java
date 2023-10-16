//package declaration
package ch.nolix.core.math.algebra;

//Java imports
import java.util.Arrays;
import java.util.Random;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalArrayHelper;
import ch.nolix.core.commontype.commontypehelper.GlobalDoubleHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link Matrix} represents a mathematical matrix of doubles. A
 * {@link Matrix} has at least 1 row and 1 column.
 * 
 * All comparisons a {@link Matrix} does are approximative. For example: -The
 * second row of the matrix [4, 5; 0.000000001, 0] is interpreted as a zero row.
 * -The matrix [0.9999999999, 0; 0, 1] is interpreted as a identity matrix.
 * 
 * @author Silvan Wyss
 * @date 2016-02-01
 */
public final class Matrix {

  //constant
  private static final Random RANDOM = new Random();

  //multi-attribute
  private double[][] values;

  //static method
  /**
   * @param size
   * @return a new identity {@link Matrix} with the given size.
   * @throws NonPositiveArgumentException if the given size is not positive.
   */
  public static Matrix createIdendityMatrix(final int size) {
    return new Matrix(size).setDiagonalValuesTo(1.0);
  }

  //static method
  /**
   * The values of the created {@link Matrix} will be all 1.0
   * 
   * @param size
   * @return a new {@link Matrix} with the given size.
   * @throws NonPositiveArgumentException if the given size is not positive.
   */
  public static Matrix createMatrixOfOnes(final int size) {
    return new Matrix(size).setAllValuesTo(1.0);
  }

  //static method
  /**
   * The values of the created {@link Matrix} will be all 1.0
   * 
   * @param rowCount
   * @param columnCount
   * @return a new {@link Matrix} with the given number of rows and the given
   *         number of columns.
   * @throws NonPositiveArgumentException if the given rowCount is not positive.
   * @throws NonPositiveArgumentException if the given columnCount is not
   *                                      positive.
   */
  public static Matrix createMatrixOfOnes(final int rowCount, final int columnCount) {
    return new Matrix(rowCount, columnCount).setAllValuesTo(1.0);
  }

  //static method
  /**
   * The values of the created {@link Matrix} will be all a whole random number in
   * [0, 99].
   * 
   * @param size
   * @return a new {@link Matrix} with the given size.
   */
  public static Matrix createRandomMatrix(final int size) {

    //Asserts that the given size is positive.
    GlobalValidator.assertThat(size).thatIsNamed(LowerCaseCatalogue.SIZE).isPositive();

    return createRandomMatrix(size, size);
  }

  //static method
  /**
   * The values of the created {@link Matrix} will be all a whole random number in
   * [0, 99].
   * 
   * @param rowCount
   * @param columnCount
   * @return a new {@link Matrix} with the given number of rows and the given
   *         number of columns.
   * @throws NonPositiveArgumentException if the given rowCount is not positive.
   * @throws NonPositiveArgumentException if the given columnCount is not
   *                                      positive.
   */
  public static Matrix createRandomMatrix(final int rowCount, final int columnCount) {

    //Creates Matrix.
    final var matrix = new Matrix(rowCount, columnCount);

    //Iterates the rows of the matrix.
    for (var i = 0; i < matrix.getRowCount(); i++) {

      //Iterates the cells of the current row.
      for (var j = 0; j < matrix.getColumnCount(); j++) {
        matrix.values[i][j] = RANDOM.nextInt(100);
      }
    }

    return matrix;
  }

  //constructor
  /**
   * Creates a new {@link Matrix} with the given size. The values of the
   * {@link Matrix} will be all 0.0.
   * 
   * @param size
   * @throws NonPositiveArgumentException if the given size is not positive.
   */
  public Matrix(final int size) {

    //Asserts that the given size is positive.
    GlobalValidator.assertThat(size).thatIsNamed(LowerCaseCatalogue.SIZE).isPositive();

    values = new double[size][size];
  }

  //constructor
  /**
   * Creates a new {@link Matrix} with the given number of rows and the given
   * number of columns.
   * 
   * @param rowCount
   * @param columnCount
   * @throws NonPositiveArgumentException if the given row count is not positive.
   * @throws NonPositiveArgumentException if the given column count is not
   *                                      positive.
   */
  public Matrix(final int rowCount, final int columnCount) {

    //Asserts that the given rowCount is positive.
    GlobalValidator.assertThat(rowCount).thatIsNamed(LowerCaseCatalogue.ROW_COUNT).isPositive();

    //Asserts that the given columnCount is positive.
    GlobalValidator.assertThat(columnCount).thatIsNamed(LowerCaseCatalogue.COLUMN_COUNT).isPositive();

    values = new double[rowCount][columnCount];
  }

  //constructor
  /**
   * Creates a new {@link Matrix} with the given number of rows and columns. The
   * values of the matrix will be all set to the given value.
   * 
   * @param rowCount
   * @param columnCount
   * @param value
   * @throws NonPositiveArgumentException if the given rowCount is not positive.
   * @throws NonPositiveArgumentException if the given columnCount is not
   *                                      positive.
   */
  public Matrix(final int rowCount, final int columnCount, final double value) {

    //Calls other constructor.
    this(rowCount, columnCount);

    setAllValuesTo(value);
  }

  //method
  /**
   * Adds the given matrix to the current {@link Matrix}.
   * 
   * @param matrix
   * @return the current {@link Matrix}.
   * @throws UnequalArgumentException if the given matrix has not as many rows as
   *                                  the current {@link Matrix}.
   * @throws UnequalArgumentException if the given matrix has not as many columns
   *                                  as the current {@link Matrix}.
   */
  public Matrix add(final Matrix matrix) {

    //Asserts that the given Matrix has as many rows as the current Matrix.
    GlobalValidator
        .assertThat(matrix.getRowCount())
        .thatIsNamed("number of rows of the given matrix")
        .isEqualTo(getRowCount());

    //Asserts that the given Matrix has as many columns as the current Matrix.
    GlobalValidator
        .assertThat(matrix.getColumnCount())
        .thatIsNamed("number of columns of the given matrix")
        .isEqualTo(getColumnCount());

    //Iterates the rows of the current Matrix.
    for (var i = 0; i < getRowCount(); i++) {

      //Iterates the cells of the current row.
      for (var j = 0; j < getColumnCount(); j++) {
        values[i][j] += matrix.values[i][j];
      }
    }

    return this;
  }

  //method
  /**
   * Appends the given matrix at the right of the current {@link Matrix}.
   * 
   * @param matrix
   * @return the current {@link Matrix}.
   * @throws UnequalArgumentException if the given matrix has not as many rows as
   *                                  the current {@link Matrix}.
   */
  public Matrix appendAtRight(final Matrix matrix) {

    //Asserts that the given Matrix has as many rows as the current Matrix.
    GlobalValidator
        .assertThat(matrix.getRowCount())
        .thatIsNamed("number of rows of the given matrix")
        .isEqualTo(getRowCount());

    final var newColumnCount = getColumnCount() + matrix.getColumnCount();
    var newValues = new double[getRowCount()][newColumnCount];

    for (var i = 0; i < getRowCount(); i++) {

      newValues[i] = Arrays.copyOf(values[i], newColumnCount);

      for (var j = 0; j < matrix.getColumnCount(); j++) {
        newValues[i][getColumnCount() + j] = matrix.values[i][j];
      }
    }

    this.values = newValues;

    return this;
  }

  //method
  /**
   * Appends a new row with the given row values on the bottom of the current
   * {@link Matrix}.
   * 
   * @param rowValue
   * @param rowValues
   * @return the current {@link Matrix}.
   * @throws InvalidArgumentException if not as many row values are given than the
   *                                  number of columns of the current
   *                                  {@link Matrix}.
   */
  public Matrix appendRowAtBottom(final double rowValue, final double... rowValues) {

    //Asserts that as many row values are given than the number of columns of the
    //current Matrix.
    final var rowValueCount = rowValues.length + 1;
    GlobalValidator.assertThat(rowValueCount).thatIsNamed("number of row value").isEqualTo(getColumnCount());

    var oldValues = values;
    values = Arrays.copyOf(values, oldValues.length + 1);
    values[getRowCount() - 1] = GlobalArrayHelper.createArrayWithValue(rowValue, rowValues);

    return this;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    return (object instanceof Matrix matrix && equals(matrix));
  }

  //method
  /**
   * @param matrix
   * @param epsilon
   * @return true if the current {@link Matrix} equals the given matrix with a
   *         deviation, that is smaller than the given epsilon.
   */
  public boolean equalsApproximatively(final Matrix matrix, final double epsilon) {

    if (!hasSameSize(matrix)) {
      return false;
    }

    for (var i = 0; i < getRowCount(); i++) {
      for (var j = 0; j < getColumnCount(); j++) {
        if (!GlobalCalculator.equalsApproximatively(matrix.values[i][j], values[i][j], epsilon)) {
          return false;
        }
      }
    }

    return true;
  }

  //method
  /**
   * @return the number of columns of the current {@link Matrix}
   */
  public int getColumnCount() {
    return values[0].length;
  }

  //method
  /**
   * @return a clone of the current {@link Matrix}
   */
  public Matrix getClone() {

    final var matrix = new Matrix(1);
    matrix.values = values;

    return matrix;
  }

  //method
  /**
   * @return the column vectors of the current {@link Matrix}
   */
  public Vector[] getColumnVectors() {

    final var columns = new Vector[getColumnCount()];

    for (var j = 0; j < getColumnCount(); j++) {

      var columnValues = new double[getRowCount()];

      for (var i = 0; i < getRowCount(); i++) {
        columnValues[i] = values[i][j];
      }

      columns[j] = Vector.withValues(columnValues);
    }

    return columns;
  }

  //method
  /**
   * @return the inverse matrix of the current {@link Matrix}.
   * @throws InvalidArgumentException if the current {@link Matrix} is not
   *                                  regular.
   */
  public Matrix getInverse() {

    assertIsQuadratic();

    final var matrix = getClone()
        .appendAtRight(Matrix.createIdendityMatrix(getRowCount()))
        .tranformFirstPartToIdentityMatrix();

    if (matrix.getRowCount() < getRowCount()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not regular");
    }

    return matrix.getMatrixWithLastColumns(getColumnCount());
  }

  //method
  /**
   * @param columnCount
   * @return a matrix with the first columns of the current {@link Matrix}.
   * @throws ArgumentIsOutOfRangeException if the given column count is not valid.
   */
  public Matrix getMatrixWithFirstColumns(int columnCount) {

    //Asserts that the given column count is valid.
    GlobalValidator
        .assertThat(columnCount)
        .thatIsNamed(LowerCaseCatalogue.COLUMN_COUNT)
        .isBetween(1, getColumnCount());

    final var matrix = new Matrix(getRowCount(), columnCount);

    for (var i = 0; i < getRowCount(); i++) {
      matrix.values[i] = Arrays.copyOf(values[i], columnCount);
    }

    return matrix;
  }

  //method
  /**
   * @param columnCount
   * @return a matrix with the last columns of the current {@link Matrix}.
   * @throws ArgumentIsOutOfRangeException if the given column count is not valid.
   */
  public Matrix getMatrixWithLastColumns(final int columnCount) {

    //Asserts that the given column count is valid.
    GlobalValidator
        .assertThat(columnCount)
        .thatIsNamed(LowerCaseCatalogue.COLUMN_COUNT)
        .isBetween(1, getColumnCount());

    final var matrix = new Matrix(getRowCount(), columnCount);

    for (var i = 0; i < getRowCount(); i++) {
      for (var j = 0; j < columnCount; j++) {
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
   * @return a matrix A so that the matrix X*A-Y is minimal. -X is the current
   *         {@link Matrix}. -Y is the given solution matrix. The following
   *         formula is used: A = (X_t*X)^-1*X_t*Y.
   * @throws UnequalArgumentException if the given solution matrix has not 1
   *                                  column.
   * @throws UnequalArgumentException if the given solution matrix has not as many
   *                                  rows as the current {@link Matrix}.
   */
  public Matrix getMinimalFactorMatrix(final Matrix solutionMatrix) {

    //Asserts that the given solution Matrix has 1 column.
    GlobalValidator
        .assertThat(solutionMatrix.getColumnCount())
        .thatIsNamed("number of columns of the given soluction matrix")
        .isEqualTo(1);

    //Asserts that the given solution Matrix has as many rows as the current
    //Matrix.
    GlobalValidator
        .assertThat(solutionMatrix.getRowCount())
        .thatIsNamed("number of rows of the given solution matrix")
        .isEqualTo(getRowCount());

    final var transposedMatrix = getTransposed();
    final var matrix = transposedMatrix.getProduct(this);
    Matrix inverseMatrix = null;

    try {
      inverseMatrix = matrix.getInverse();
    } catch (final Throwable error //NOSONAR: If there does not exist an inverse matrix, a pseudo-inverse matrix
                                   //will be calculated.
    ) {
      inverseMatrix = matrix.getPseudoInverse();
    }

    return inverseMatrix
        .getProduct(transposedMatrix)
        .getProduct(solutionMatrix);
  }

  //method
  /**
   * @param matrix
   * @return the product of the current {@link Matrix} and the given matrix.
   * @throws UnequalArgumentException if the given matrix has not as many rows as
   *                                  the current {@link Matrix} columns has.
   */
  public Matrix getProduct(final Matrix matrix) {

    //Asserts that the given Matrix has as many rows as the number of columns of
    //the current Matrix.
    GlobalValidator
        .assertThat(matrix.getRowCount())
        .thatIsNamed("number of rows of the given matrix")
        .isEqualTo(getColumnCount());

    final var product = new Matrix(getRowCount(), matrix.getColumnCount());

    for (var i = 0; i < product.getRowCount(); i++) {
      for (var j = 0; j < product.getColumnCount(); j++) {
        for (var k = 0; k < getColumnCount(); k++) {
          product.values[i][j] += values[i][k] * matrix.values[k][j];
        }
      }
    }

    return product;
  }

  //method
  /**
   * @return a pseudo inverse matrix of the current {@link Matrix}.
   * @throws InvalidArgumentException if the current {@link Matrix} is not
   *                                  quadratic.
   */
  public Matrix getPseudoInverse() {

    //Asserts that the current Matrix is quadratic.
    assertIsQuadratic();

    return getSum(new Matrix(getRowCount())
        .setDiagonalValuesTo(0.001))
        .getInverse();
  }

  //method
  /**
   * @return the rank of the current {@link Matrix}.
   * @throws InvalidArgumentException if the current {@link Matrix} is not
   *                                  quadratic.
   */
  public int getRank() {

    //Asserts that the current Matrix is quadratic.
    assertIsQuadratic();

    return getClone().transformToEquivalentUpperLeftMatrix().getRowCount();
  }

  //method
  /**
   * @return the number of rows of the current {@link Matrix}
   */
  public int getRowCount() {
    return values.length;
  }

  //method
  /**
   * @return the row vectors of the current {@link Matrix}
   */
  public Vector[] getRowVectors() {

    final var rows = new Vector[getRowCount()];

    for (var i = 0; i < getRowCount(); i++) {
      rows[i] = Vector.withValues(values[i]);
    }

    return rows;
  }

  //method
  /**
   * @return the size of the current {@link Matrix}
   */
  public int getSize() {
    return (getRowCount() * getColumnCount());
  }

  //method
  /**
   * @return the solution when the current {@link Matrix} is an extended matrix of
   *         a linear equation system
   */
  public double[] getSolutionAsExtendedMatrix() {

    final var matrix = getClone().transformToEquivalentUpperLeftMatrix();

    if (matrix.getRowCount() != getRowCount()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not have a regular coefficient Matrix");
    }

    final var solution = new double[getRowCount()];

    for (var i = matrix.getRowCount() - 1; i >= 0; i--) {

      var sum = matrix.values[i][matrix.getColumnCount() - 1];

      for (var j = matrix.getColumnCount() - 2; j > i; j--) {
        sum -= matrix.values[i][j] * solution[j];
      }

      solution[i] = sum / matrix.values[i][i];
    }

    return solution;
  }

  //method
  /**
   * @param matrix
   * @return the matrix that is the sum of the current {@link Matrix} and the
   *         given matrix
   * @throws UnequalArgumentException if the given matrix has not the same size as
   *                                  the current {@link Matrix}
   */
  public Matrix getSum(final Matrix matrix) {
    return getClone().add(matrix);
  }

  //method
  /**
   * @return the transposed matrix of the current {@link Matrix}.
   */
  public Matrix getTransposed() {
    return getClone().transpose();
  }

  //method
  /**
   * @return the trace of the current {@link Matrix}.
   * @throws InvalidArgumentException if the current {@link Matrix} is not
   *                                  quadratic.
   */
  public double getTrace() {

    //Asserts that the current Matrix is quadratic.
    assertIsQuadratic();

    var trace = 0.0;
    for (var i = 0; i < getRowCount(); i++) {
      trace += values[i][i];
    }

    return trace;
  }

  //method
  /**
   * @param rowIndex
   * @param columnIndex
   * @return the value in the row with the given row index and the column with the
   *         given column index.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       contain a row with the given row index.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       contain a column with the given column
   *                                       index.
   */
  public double getValue(final int rowIndex, final int columnIndex) {

    //Asserts that the current Matrix contains a row with the given row index.
    GlobalValidator
        .assertThat(rowIndex)
        .thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
        .isBetween(1, getRowCount());

    //Asserts that the current Matrix contains a column with the given column
    //index.
    GlobalValidator
        .assertThat(columnIndex)
        .thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
        .isBetween(1, getColumnCount());

    return values[rowIndex - 1][columnIndex - 1];
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  /**
   * @param matrix
   * @return true if the current {@link Matrix} has the same size as the given
   *         matrix
   */
  public boolean hasSameSize(Matrix matrix) {
    return (getRowCount() == matrix.getRowCount() && getColumnCount() == matrix.getColumnCount());
  }

  //method
  /**
   * @return true if the current {@link Matrix} is an identity matrix
   */
  public boolean isIdentityMatrix() {

    if (!isQuadratic()) {
      return false;
    }

    final var rowCount = getRowCount();
    for (var i = 1; i <= rowCount; i++) {
      if (!canBeLineInIdentityMatrix(i)) {
        return false;
      }

    }

    return true;
  }

  //method
  /**
   * @return true if the current {@link Matrix} is a quadratic matrix
   */
  public boolean isQuadratic() {
    return (getRowCount() == getColumnCount());
  }

  //method
  /**
   * @return true if the current {@link Matrix} is regular
   */
  public boolean isRegular() {
    return (isQuadratic() && getRank() == getRowCount());
  }

  //method
  /**
   * Multiplies the current {@link Matrix} with the given factor.
   * 
   * @param factor
   * @return the current {@link Matrix}
   */
  public Matrix multiply(double factor) {

    for (var i = 0; i < getRowCount(); i++) {
      for (var j = 0; j < getColumnCount(); j++) {
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
   * @return the current {@link Matrix}.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       contain a row with the given row index.
   */
  public Matrix multiplyRow(final int rowIndex, final double factor) {

    //Asserts that the current Matrix contains a row with the given row index.
    GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isBetween(1, getRowCount());

    //Iterates the cells of the row with the given row index.
    for (var i = 0; i < getColumnCount(); i++) {
      values[rowIndex][i] *= factor;
    }

    return this;
  }

  //method
  /**
   * Removes the zero rows of the current {@link Matrix} using an epsilon
   * 
   * @return the current {@link Matrix}
   */
  public Matrix removeZeroRows() {

    final var newValues = new LinkedList<double[]>();

    for (double[] r : values) {

      var isZeroRow = true;

      for (double v : r) {
        if (!GlobalCalculator.equalsApproximatively(v, 0.0)) {
          isZeroRow = false;
          break;
        }
      }

      if (!isZeroRow) {
        newValues.addAtEnd(r);
      }
    }

    values = new double[newValues.getElementCount()][values.length];
    for (var i = 0; i < newValues.getElementCount(); i++) {
      values[i] = newValues.getStoredAt1BasedIndex(i + 1);
    }

    return this;
  }

  //method
  /**
   * Sets the values of the current {@link Matrix} to the given value.
   * 
   * @param value
   * @return the current {@link Matrix}
   */
  public Matrix setAllValuesTo(double value) {

    for (var i = 0; i < values.length; i++) {
      for (var j = 0; j < values[i].length; j++) {
        values[i][j] = value;
      }
    }

    return this;
  }

  //method
  /**
   * Sets the value in the row with the given row index and the column with the
   * given column index.
   * 
   * @param rowIndex
   * @param columnIndex
   * @param value
   * @return the current {@link Matrix}.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       contain a row with the given row index.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       contain a column with the given column
   *                                       index.
   */
  public Matrix setValue(final int rowIndex, final int columnIndex, final double value) {

    //Asserts that the current Matrix contains a row with the given row index.
    GlobalValidator
        .assertThat(rowIndex)
        .thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
        .isBetween(1, getRowCount());

    //Asserts that the current Matrix contains a column with the given column
    //index.
    GlobalValidator
        .assertThat(columnIndex)
        .thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
        .isBetween(1, getColumnCount());

    values[rowIndex - 1][columnIndex - 1] = value;

    return this;
  }

  //method
  /**
   * Sets the values of the current {@link Matrix}.
   * 
   * @param value
   * @param values
   * @return the current {@link Matrix}.
   * @throws InvalidArgumentException if not as many values are given as the
   *                                  current {@link Matrix} contains.
   */
  public Matrix setValues(final double value, final double... values) {

    //Asserts that as many values are given as the current Matrix contains.
    final var valueCount = 1 + values.length;
    GlobalValidator.assertThat(valueCount).isEqualTo(getColumnCount() * getRowCount());

    this.values[0][0] = value;

    for (var j = 1; j < getColumnCount(); j++) {
      this.values[0][j] = values[j - 1];
    }

    //Iterates the rows of the current Matrix.
    for (var i = 1; i < getRowCount(); i++) {

      //Iterates the cells of the current row.
      for (var j = 0; j < getColumnCount(); j++) {
        this.values[i][j] = values[i * getColumnCount() + j - 1];
      }
    }

    return this;
  }

  //method
  /**
   * Sets the values of the current {@link Matrix}.
   * 
   * @param values
   * @return the current {@link Matrix}.
   * @throws InvalidArgumentException if not as many values are given as the
   *                                  current {@link Matrix} contains.
   */
  public Matrix setValues(final double[] values) {

    //Asserts that as many values are given as the current Matrix contains.
    GlobalValidator.assertThat(values).hasElementCount(getColumnCount() * getRowCount());

    //Iterates the rows of the current Matrix.
    for (var i = 0; i < getRowCount(); i++) {

      //Iterates the cells of the current row.
      for (var j = 0; j < getColumnCount(); j++) {
        this.values[i][j] = values[i * getColumnCount() + j];
        this.values[i][j] = values[i * getColumnCount() + j - 1];
      }
    }

    return this;
  }

  //method
  /**
   * Sets the diagonal values of the current {@link Matrix} to the given value.
   * 
   * @param value
   * @return the current {@link Matrix}
   * @throws InvalidArgumentException if the current {@link Matrix} is not
   *                                  quadratic
   */
  public Matrix setDiagonalValuesTo(double value) {

    assertIsQuadratic();

    for (var i = 0; i < values.length; i++) {
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
   * @return the current {@link Matrix}.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       have a row with the given row1 index.
   * @throws ArgumentIsOutOfRangeException if the current {@link Matrix} does not
   *                                       have a row with the given row1 index.
   */
  public Matrix swapRows(int row1Index, int row2Index) {

    //Asserts that the current Matrix has a row with the given row 1 index.
    GlobalValidator.assertThat(row1Index).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isBetween(1, getRowCount());

    //Asserts that the current Matrix has a row with the given row 2 index.
    GlobalValidator.assertThat(row2Index).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isBetween(1, getRowCount());

    final double[] temp = values[row1Index - 1];
    values[row1Index - 1] = values[row2Index - 1];
    values[row2Index - 1] = temp;

    return this;
  }

  //method
  /**
   * @return a polynom representation of the current {@link Matrix}
   * @throws UnrepresentingArgumentException if the current {@link Matrix} does
   *                                         not represent a {@link Polynom}.
   */
  public Polynom toPolynom() {

    //Asserts that the upper left element of the current {@link Matrix} is 0.
    if (values[0][0] == 0) {
      throw UnrepresentingArgumentException.forArgumentAndType(this, Polynom.class);
    }

    //Handles the case that the current Matrix consists of 1 row.
    if (getRowCount() == 1) {
      return Polynom.withCoefficients(values[0]);
    }

    //Handles the case that the current Matrix consists of 1 column.
    if (getColumnCount() == 1) {

      final var lValues = new double[getRowCount()];

      for (var i = 0; i < getRowCount(); i++) {
        lValues[i] = this.values[i][0];
      }

      return Polynom.withCoefficients(lValues);
    }

    //Handles the case that the current Matrix does not consist of 1 row nor of 1
    //column.
    throw UnrepresentingArgumentException.forArgumentAndType(this, Polynom.class);
  }

  //method
  /**
   * @return a vector representation of the current {@link Matrix}
   * @throws UnrepresentingArgumentException if the current {@link Matrix} does
   *                                         not represent a {@link Vector}.
   */
  public Vector toVector() {

    //Handles the case that the current Matrix contains 1 row.
    if (getRowCount() == 1) {
      return Vector.withValues(values[0]);
    }

    //Handles the case that the current Matrix contains 1 column.
    if (getColumnCount() == 1) {

      final var lValues = new double[getRowCount()];

      for (var i = 0; i < getRowCount(); i++) {
        lValues[i] = this.values[i][0];
      }

      return Vector.withValues(lValues);
    }

    //Handles the case that the current Matrix does not either contain 1 row nor 1
    //column.
    throw UnrepresentingArgumentException.forArgumentAndType(this, Vector.class);
  }

  //method
  /**
   * Transforms the first part of the current {@link Matrix} to an identity
   * matrix.
   * 
   * @return the current {@link Matrix}
   * @throws InvalidArgumentException if the current {@link Matrix} has more rows
   *                                  than columns.
   * @throws InvalidArgumentException if the current {@link Matrix} has linear
   *                                  depending rows.
   */
  public Matrix tranformFirstPartToIdentityMatrix() {

    final var rowCount = getRowCount();

    //Asserts that the current Matrix has not more rows than columns.
    if (rowCount > getColumnCount()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has more rows than columns");
    }

    //Transforms the current Matrix to an equivalent upper left matrix.
    transformToEquivalentUpperLeftMatrix();

    //Asserts that the current Matrix does not have a linear depending rows.
    if (rowCount != getRowCount()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has linear depending rows");
    }

    //Iterates the rows of the current Matrix.
    for (var i = getRowCount() - 1; i >= 0; i--) {

      if (GlobalCalculator.equalsApproximatively(values[i][i], 0.0)) {
        throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has linear depending rows");
      }

      final var factor = 1 / values[i][i];
      for (var j = 0; j < getColumnCount(); j++) {
        values[i][j] *= factor;
      }

      for (var j = i + 1; j < getRowCount(); j++) {

        final var factor2 = values[i][j];

        for (var k = 0; k < getColumnCount(); k++) {
          values[i][k] -= factor2 * values[j][k];
        }
      }
    }

    return this;
  }

  //method
  /**
   * Transforms the current {@link Matrix} to an equivalent upper left matrix.
   * 
   * @return the current {@link Matrix}
   */
  @SuppressWarnings("all")
  public Matrix transformToEquivalentUpperLeftMatrix() {

    var minColumnIndex = 0;

    //Iterate over rows of the current {@link Matrix}.
    for (var i = 0; i < values.length; i++) {

      var found = false;

      while (!found && minColumnIndex < values[i].length) {

        //Iterates over the current row and the rows below the current row.
        for (var j = i; j < values.length; j++) {
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
        for (var j = i + 1; j < values.length; j++) {

          var factor = -values[j][minColumnIndex] / values[i][minColumnIndex];

          //Iterates the values of the row.
          for (int k = minColumnIndex; k < values[j].length; k++) {
            values[j][k] += factor * values[i][k];
          }
        }
      } else {
        break;
      }

      //Updates min column index.
      minColumnIndex++;
    }

    return removeZeroRows();
  }

  //method
  /**
   * @return a {@link String} representation of this object
   */
  @Override
  public String toString() {

    final var stringBuilder = new StringBuilder();

    stringBuilder.append('[');

    for (var i = 0; i < getRowCount(); i++) {

      for (var j = 0; j < getColumnCount(); j++) {

        stringBuilder.append(GlobalDoubleHelper.toString(values[i][j]));

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
   * Transposes the current {@link Matrix}.
   * 
   * @return the current {@link Matrix}
   */
  public Matrix transpose() {

    final var lValues = new double[getColumnCount()][getRowCount()];

    for (var i = 0; i < getRowCount(); i++) {
      for (var j = 0; j < getColumnCount(); j++) {
        lValues[j][i] = this.values[i][j];
      }
    }

    this.values = lValues;

    return this;
  }

  //method
  /**
   * @throws InvalidArgumentException if the current {@link Matrix} is not
   *                                  quadratic.
   */
  private void assertIsQuadratic() {
    if (!isQuadratic()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          this,
          "is not quadratic");
    }
  }

  //method
  /**
   * @param lineIndex
   * @return true if the line with the given index of the current {@link Matrix}
   *         allows to the current {@link Matrix} to be a identity {@link Matrix}.
   */
  private boolean canBeLineInIdentityMatrix(final int lineIndex) {

    final var columnCount = getColumnCount();
    for (var j = 0; j < columnCount; j++) {
      if (lineIndex != j) {
        if (!GlobalCalculator.isApproximatelyZero(values[lineIndex - 1][j])) {
          return false;
        }
      } else if ( //NOSONAR: The else-case is continuing the loop.
      !GlobalCalculator.isApproximatelyOne(values[lineIndex - 1][j])) {
        return false;
      }
    }

    return true;
  }

  //method
  /**
   * @param matrix
   * @return true if the current {@link Matrix} equals the given matrix.
   */
  private boolean equals(final Matrix matrix) {

    if (!hasSameSize(matrix)) {
      return false;
    }

    for (var i = 0; i < getRowCount(); i++) {
      for (var j = 0; j < getColumnCount(); j++) {
        if (matrix.values[i][j] != values[i][j]) {
          return false;
        }
      }
    }

    return true;
  }
}
