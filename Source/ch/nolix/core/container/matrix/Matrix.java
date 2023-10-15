//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Arrays;
import java.util.function.Function;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.matrixapi.IMatrix;
import ch.nolix.coreapi.containerapi.matrixapi.IMutableMatrix;

//class
/**
 * A {@link Matrix} is a {@link Container} that stores its elements in rows and
 * columns. A {@link Matrix} is clearable.
 * 
 * @author Silvan Wyss
 * @date 2016-08-01
 * @param <E> is the type of the elements of a {@link Matrix}.
 */
public final class Matrix<E> extends Container<E> implements IMutableMatrix<E> {

  // static method
  /**
   * @param matrix
   * @param <E2>   is the type of the elements of the given matrix.
   * @return a new {@link Matrix} with the size and elements of the given matrix.
   */
  public static <E2> Matrix<E2> fromMatrix(final IMatrix<E2> matrix) {

    final var newMatrix = new Matrix<E2>();

    for (final var r : matrix.getRows()) {
      newMatrix.addRow(r);
    }

    return newMatrix;
  }

  // multi-attribute
  private Object[][] elements = new Object[0][0];

  // method
  /**
   * Adds a new column to the current {@link Matrix} with the given elements. The
   * complexity of this implementation is O(m + n) if: -The current {@link Matrix}
   * contains m elements. -n elements are given.
   * 
   * @param element
   * @param elements
   * @return the current {@link Matrix}.
   * @throws ArgumentIsNullException  if the given elements is null.
   * @throws ArgumentIsNullException  if one of the given elements is null.
   * @throws UnequalArgumentException if the current {@link Matrix} is not empty
   *                                  and if not as many elements are given as the
   *                                  number of rows of the current
   *                                  {@link Matrix}.
   */
  @SuppressWarnings("unchecked")
  public Matrix<E> addColumn(final E element, final E... elements) {

    // Calls other method.
    return addColumn(ReadContainer.forElement(element, elements));
  }

  // method
  /**
   * Adds a new column to the current {@link Matrix} with the given elements. The
   * complexity of this implementation is O(m + n) if: -The current {@link Matrix}
   * contains m elements. -n elements are given.
   * 
   * @param elements
   * @return the current {@link Matrix}.
   * @throws ArgumentIsNullException  if the given elements is null.
   * @throws ArgumentIsNullException  if one of the given elements is null.
   * @throws UnequalArgumentException if the current {@link Matrix} is not empty
   *                                  and if not as many elements are given as the
   *                                  number of rows of the current
   *                                  {@link Matrix}.
   */
  public Matrix<E> addColumn(final Iterable<E> elements) {

    // Asserts that the given elements are not null.
    GlobalValidator.assertThatTheElements(elements).areNotNull();

    final var lElements = ReadContainer.forIterable(elements);

    // Handles the case that the current {@link Matrix} is empty.
    if (isEmpty()) {
      if (lElements.containsAny()) {

        this.elements = new Object[lElements.getElementCount()][1];

        // Iterates the given elements.
        var i = 0;
        for (final var e : lElements) {

          this.elements[i][0] = e;

          i++;
        }
      }

      // Handles the case that the current matrix is not empty.
    } else {

      // Asserts that as many elements are given as the number of rows of the current
      // matrix.
      GlobalValidator
          .assertThat(lElements.getElementCount())
          .thatIsNamed("number of the given elements")
          .isEqualTo(getRowCount());

      final var columnCount = getColumnCount();

      // Iterates the given elements.
      var i = 0;
      for (final var e : lElements) {

        final var row = Arrays.copyOf(this.elements[i], columnCount + 1);
        row[columnCount] = e;

        this.elements[i] = row;

        i++;
      }
    }

    return this;
  }

  // method
  /**
   * Adds a new row to the current {@link Matrix} with the given elements. The
   * complexity of this implementation is O(m + n) if: -The current {@link Matrix}
   * contains m rows. -n elements are given.
   * 
   * @param element
   * @param elements
   * @return the current {@link Matrix}.
   * @throws ArgumentIsNullException  if the given elements is null.
   * @throws ArgumentIsNullException  if one of the given elements is null.
   * @throws UnequalArgumentException the current {@link Matrix} is not empty and
   *                                  if not as many elements are given as the
   *                                  number of columns of the current
   *                                  {@link Matrix}.
   */
  @SuppressWarnings("unchecked")
  public Matrix<E> addRow(final E element, final E... elements) {

    // Calls other method.
    return addRow(ReadContainer.forElement(element, elements));
  }

  // method
  /**
   * Adds a new row to the current {@link Matrix} with the given elements. The
   * complexity of this implementation is O(m + n) if: -The current {@link Matrix}
   * contains m rows. -n elements are given.
   * 
   * @param elements
   * @return the current {@link Matrix}.
   * @throws ArgumentIsNullException  if the given elements is null.
   * @throws ArgumentIsNullException  if one of the given elements is null.
   * @throws UnequalArgumentException the current {@link Matrix} is not empty and
   *                                  if not as many elements are given as the
   *                                  number of columns of the current
   *                                  {@link Matrix}.
   */
  public Matrix<E> addRow(final Iterable<E> elements) {

    // Asserts that the given elements are not null.
    GlobalValidator.assertThatTheElements(elements).areNotNull();

    final var lElements = ReadContainer.forIterable(elements);

    // Handles the case that the current matrix is empty.
    if (isEmpty()) {
      if (lElements.containsAny()) {

        this.elements = new Object[1][lElements.getElementCount()];

        // Iterates the given elements.
        var i = 0;
        for (final var e : lElements) {

          this.elements[0][i] = e;

          i++;
        }
      }

      // Handles the case that the current matrix is not empty.
    } else {

      // Asserts that as many elements are given as the number of columns of the
      // current matrix.
      GlobalValidator
          .assertThat(lElements.getElementCount())
          .thatIsNamed("number of the given elements")
          .isEqualTo(getColumnCount());

      final var rowCount = getRowCount();
      final var newElements = Arrays.copyOf(this.elements, rowCount + 1);
      newElements[rowCount] = new Object[getColumnCount()];

      // Iterates the given elements.
      var i = 0;
      for (final var e : lElements) {

        newElements[rowCount][i] = e;

        i++;
      }

      this.elements = newElements;
    }

    return this;
  }

  // method
  /**
   * Removes all elements of the current {@link Matrix}. The complexity of this
   * implementation is O(1).
   */
  @Override
  public void clear() {
    elements = new Object[0][0];
  }

  // method
  /**
   * @param columnIndex
   * @return the column of the current {@link Matrix} with the given column index.
   * @throws NonPositiveArgumentException if the given column index is not
   *                                      positive.
   * @throws BiggerArgumentException      if the given column index is bigger than
   *                                      the number of columns of the current
   *                                      {@link Matrix}.
   */
  public MatrixColumn<E> getColumn(final int columnIndex) {
    return new MatrixColumn<>(this, columnIndex);
  }

  // method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return the number of columns of the current {@link Matrix}.
   */
  @Override
  public int getColumnCount() {

    // Handles the case that the current {@link Matrix} is empty.
    if (elements.length < 1) {
      return 0;
    }

    // Handles the case that the current {@link Matrix} is not empty.
    return elements[0].length;
  }

  // method
  /**
   * @param index
   * @return the index of the column of the element of the current {@link Matrix}
   *         at the given index.
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   */
  public int getColumnIndexOf(final int index) {

    // Asserts that the current matrix contains an element at the given index.
    assertContainsAt(index);

    final var columnCount = getColumnCount();

    if (columnCount == 0) {
      throw EmptyArgumentException.forArgument(this);
    }

    return ((index - 1) % columnCount + 1);
  }

  // method
  /**
   * @return the columns of the current {@link Matrix}.
   */
  @Override
  public IContainer<MatrixColumn<E>> getColumns() {

    final var columns = new LinkedList<MatrixColumn<E>>();

    // Iterates the columns of the current matrix.
    for (var i = 1; i <= getColumnCount(); i++) {
      columns.addAtEnd(new MatrixColumn<>(this, i));
    }

    return columns;
  }

  // method
  /**
   * The complexity of this implementation is O(m * n) if: -This matrix contains m
   * rows. -This matrix contains n columns.
   * 
   * @return a new {@link Matrix} with the elements of the current {@link Matrix}.
   */
  public Matrix<E> getCopy() {

    final var matrix = new Matrix<E>();

    final var rowCounnt = getRowCount();
    final var columnCount = getColumnCount();

    matrix.elements = new Object[rowCounnt][columnCount];
    for (var i = 0; i < rowCounnt; i++) {
      matrix.elements[i] = Arrays.copyOf(elements[i], columnCount);
    }

    return matrix;
  }

  // method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return the number of elements of the current {@link Matrix}.
   */
  @Override
  public int getElementCount() {
    return (getRowCount() * getColumnCount());
  }

  // method
  /**
   * @param rowIndex
   * @param columnIndex
   * @return the index of the element of the current {@link Matrix} at the given
   *         row index and column index.
   * @throws NonPositiveArgumentException if the given row index is not positive.
   * @throws BiggerArgumentException      if the given row index is bigger than
   *                                      the number of rows of the current
   *                                      {@link Matrix}.
   * @throws NonPositiveArgumentException if the given column index is not
   *                                      positive.
   * @throws BiggerArgumentException      if the given column index is bigger than
   *                                      the number of columns of the current
   *                                      {@link Matrix}.
   */
  public int getIndexOf(final int rowIndex, final int columnIndex) {

    // Asserts that the current matrix contains an element
    // at the given row index and the given column index.
    assertContainsAt(rowIndex, columnIndex);

    return ((rowIndex - 1) * getColumnCount() + columnIndex);
  }

  // method
  /**
   * @return the element of the current {@link Matrix} at the given index .
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {
    return getStoredAt1BasedRowIndexAndColumnIndex(getRowIndexOf(p1BasedIndex), getColumnIndexOf(p1BasedIndex));
  }

  // method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @param p1BasedRowIndex
   * @param p1BasedColumnIndex
   * @return the element of the current {@link Matrix} at the given row index and
   *         column index.
   * @throws NonPositiveArgumentException if the given row index is not positive.
   * @throws BiggerArgumentException      if the given row index is bigger than
   *                                      the number of rows of the current
   *                                      {@link Matrix}.
   * @throws NonPositiveArgumentException if the given column index is not
   *                                      positive.
   * @throws BiggerArgumentException      if the given column index is bigger than
   *                                      the number of columns of the current
   *                                      {@link Matrix}.
   */
  @Override
  @SuppressWarnings("unchecked")
  public E getStoredAt1BasedRowIndexAndColumnIndex(final int p1BasedRowIndex, final int p1BasedColumnIndex) {

    // Asserts that the current matrix contains an element at the given row index
    // and column index.
    assertContainsAt(p1BasedRowIndex, p1BasedColumnIndex);

    return (E) elements[p1BasedRowIndex - 1][p1BasedColumnIndex - 1];
  }

  // method
  // For a better performance, this implementation does not use all comfortable
  // methods.
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public E getStoredLast() {
    return (E) elements[getRowCount()][getColumnCount()];
  }

  // method
  /**
   * @param rowIndex
   * @return the row of the current {@link Matrix} at the given row index.
   * @throws NonPositiveArgumentException if the given row index is not positive.
   * @throws BiggerArgumentException      if the given row index is bigger than
   *                                      the number of rows of the current
   *                                      {@link Matrix}.
   */
  public MatrixRow<E> getRow(final int rowIndex) {
    return new MatrixRow<>(this, rowIndex);
  }

  // method
  /**
   * @param index
   * @return the index of the row of the element of the current {@link Matrix} at
   *         the given index.
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   */
  public int getRowIndexOf(final int index) {

    // Asserts that the current matrix contains an element at the given index.
    assertContainsAt(index);

    final var columnCount = getColumnCount();

    if (columnCount == 0) {
      throw EmptyArgumentException.forArgument(this);
    }

    return ((index - 1) / columnCount + 1);
  }

  // method
  /**
   * @return the rows of the current {@link Matrix}.
   */
  @Override
  public IContainer<MatrixRow<E>> getRows() {

    final var rows = new LinkedList<MatrixRow<E>>();

    // Iterates the rows of the current matrix.
    for (var i = 1; i <= getRowCount(); i++) {
      rows.addAtEnd(new MatrixRow<>(this, i));
    }

    return rows;
  }

  // method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return the number of rows of the current {@link Matrix}.
   */
  @Override
  public int getRowCount() {

    // Handles the case that the current {@link Matrix} is empty.
    if (elements.length < 1) {
      return 0;
    }

    // Handles the case that the current {@link Matrix} is not empty.
    return elements.length;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  // method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return a new iterator for the current {@link Matrix}.
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MatrixIterator.forMatrix(this);
  }

  // method
  /**
   * Sets the given element to the current {@link Matrix} at the given index. The
   * complexity of this implementation is O(1).
   * 
   * @param index
   * @param element
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   * @throws ArgumentIsNullException      if the given element is null.
   */
  public void setAt(final int index, final E element) {

    // Asserts that the given element is not null.
    GlobalValidator
        .assertThat(element)
        .thatIsNamed(LowerCaseCatalogue.ELEMENT)
        .isNotNull();

    // Sets the given element at the given index to the current matrix.
    elements[getRowIndexOf(index) - 1][getColumnIndexOf(index) - 1] = element;
  }

  // method
  /**
   * Sets the given element to the current {@link Matrix} to the row with the
   * given row index and the column with the given column index.
   * 
   * The complexity of this implementation is O(1).
   * 
   * @param p1BasedRowIndex
   * @param p1BasedColumnIndex
   * @param element
   * @throws NonPositiveArgumentException if the given row index is not positive.
   * @throws BiggerArgumentException      if the given row index is bigger than
   *                                      the number of rows of the current
   *                                      {@link Matrix}.
   * @throws NonPositiveArgumentException if the given column index is not
   *                                      positive.
   * @throws BiggerArgumentException      if the given column index is bigger than
   *                                      the number of columns of the current
   *                                      {@link Matrix}.
   * @throws ArgumentIsNullException      if the given element is null.
   */
  @Override
  public void setAt1BasedRowIndexAndColumnIndex(
      final int p1BasedRowIndex,
      final int p1BasedColumnIndex,
      final E element) {

    // Asserts that the current matrix contains an element at the given row index
    // and column index.
    assertContainsAt(p1BasedRowIndex, p1BasedColumnIndex);

    // Asserts that the given element is not null.
    GlobalValidator
        .assertThat(element)
        .thatIsNamed(LowerCaseCatalogue.ELEMENT)
        .isNotNull();

    elements[p1BasedRowIndex - 1][p1BasedColumnIndex - 1] = element;
  }

  // method
  /**
   * The complexity of this implementation is O(n) if: -This matrix contains n
   * elements. -The given transformer has a complexity of O(1).
   * 
   * @param transformer
   * @param <O>         is the type of the elements the given transformer returns.
   * @return a new matrix with the elements the given transformer transforms of
   *         the elements of the current {@link Matrix}.
   */
  @SuppressWarnings("unchecked")
  public <O> Matrix<O> toMatrix(final Function<E, O> transformer) {

    // Creates matrix.
    final var matrix = new Matrix<O>();

    // Fills up the elements of the matrix.
    matrix.elements = new Object[getRowCount()][getColumnCount()];
    for (var i = 0; i < getRowCount(); i++) {

      // Iterates the columns of the current row.
      for (var j = 0; j < getColumnCount(); j++) {
        matrix.elements[i][j] = transformer.apply((E) elements[i][j]);
      }
    }

    return matrix;
  }

  // method
  // For a better performance, this implementation does not use all comfortable
  // methods.
  /**
   * The complexity of this implementation is O(n) if the current {@link Matrix}
   * contains n elements.
   * 
   * @return a new left rotated {@link Matrix} of the current {@link Matrix}.
   */
  public Matrix<E> toLeftRotatedMatrix() {

    final var leftRotatedMatrix = new Matrix<E>();
    final var leftRotatedMatrixRowCount = getColumnCount();
    final var leftRotatedMatrixColumnCount = getRowCount();

    final var leftRotatedMatrixElements = new Object[leftRotatedMatrixRowCount][leftRotatedMatrixColumnCount];

    leftRotatedMatrix.elements = leftRotatedMatrixElements;

    // Iterates the rows of the left rotated matrix.
    for (var i = 0; i < leftRotatedMatrixRowCount; i++) {

      // Iterates the columns of the current row.
      for (var j = 0; j < leftRotatedMatrixColumnCount; j++) {
        leftRotatedMatrixElements[i][j] = elements[j][leftRotatedMatrixRowCount - i - 1];
      }
    }

    return leftRotatedMatrix;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  // method
  // For a better performance, this implementation does not use all comfortable
  // methods.
  /**
   * The complexity of this implementation is O(n) if the current {@link Matrix}
   * contains n elements.
   * 
   * @return a new right rotated {@link Matrix} of the current {@link Matrix}.
   */
  public Matrix<E> toRightRotatedMatrix() {

    final var rightRotatedMatrix = new Matrix<E>();
    final var rightRotatedMatrixRowCount = getColumnCount();
    final var rightRotatedMatrixColumnCount = getRowCount();

    final var rightRotatedMatrixElements = new Object[rightRotatedMatrixRowCount][rightRotatedMatrixColumnCount];

    rightRotatedMatrix.elements = rightRotatedMatrixElements;

    // Iterates the rows of the right rotated matrix.
    for (var i = 0; i < rightRotatedMatrixRowCount; i++) {

      // Iterates the columns of the current row.
      for (var j = 0; j < rightRotatedMatrixColumnCount; j++) {
        rightRotatedMatrixElements[i][j] = elements[rightRotatedMatrixColumnCount - j - 1][i];
      }
    }

    return rightRotatedMatrix;
  }

  // method
  /**
   * The complexity of this implementation is O(n) if: -The current {@link Matrix}
   * contains n elements. -The toString method of the elements of the current
   * {@link Matrix} has a complexity of O(1).
   * 
   * @return a {@link String} representation of the current {@link Matrix}.
   */
  @Override
  public String toString() {

    final var stringBuilder = new StringBuilder();

    // Iterates the rows of the current matrix.
    for (var i = 0; i < getRowCount(); i++) {

      if (i > 0) {
        stringBuilder.append(CharacterCatalogue.SEMICOLON);
      }

      // Iterates the columns of the current row.
      for (var j = 0; j < getColumnCount(); j++) {

        if (j > 0) {
          stringBuilder.append(CharacterCatalogue.COMMA);
        }

        stringBuilder.append(elements[i][j].toString());
      }
    }

    return stringBuilder.toString();
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return new LinkedList<>();
  }

  // method
  /**
   * @param index
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   */
  private void assertContainsAt(final int index) {

    GlobalValidator
        .assertThat(index)
        .thatIsNamed(LowerCaseCatalogue.INDEX)
        .isPositive();

    GlobalValidator
        .assertThat(index)
        .thatIsNamed(LowerCaseCatalogue.INDEX)
        .isNotBiggerThan(getElementCount());
  }

  // method
  /**
   * @param rowIndex
   * @param columnIndex
   * @throws NonPositiveArgumentException if the given row index is not positive.
   * @throws BiggerArgumentException      if the given row index is bigger than
   *                                      the number of rows of the current
   *                                      {@link Matrix}.
   * @throws NonPositiveArgumentException if the given column index is not
   *                                      positive.
   * @throws BiggerArgumentException      if the given column index is bigger than
   *                                      the number of columns of the current
   *                                      {@link Matrix}.
   */
  private void assertContainsAt(final int rowIndex, final int columnIndex) {

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
}
