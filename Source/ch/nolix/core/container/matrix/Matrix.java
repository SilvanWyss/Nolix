package ch.nolix.core.container.matrix;

import java.util.Arrays;
import java.util.function.Function;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.container.matrix.IMatrix;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link Matrix} is a {@link AbstractContainer} that stores its elements in
 * rows and columns. A {@link Matrix} is clearable.
 * 
 * @author Silvan Wyss
 * @version 2016-08-01
 * @param <E> is the type of the elements of a {@link Matrix}.
 */
public final class Matrix<E> extends AbstractExtendedContainer<E> implements IMatrix<E> {
  private Object[][] memberElements = new Object[0][0];

  /**
   * Creates a new empty {@link Matrix}.
   */
  private Matrix() {
  }

  /**
   * @return a new empty {@link Matrix}.
   * @param <E2> is the type of the elements of the created {@link Matrix}.
   */
  public static <E2> Matrix<E2> createEmpty() {
    return new Matrix<>();
  }

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
    //Collects allElements.
    final var allElements = ContainerView.forElementAndArray(element, elements);

    //Calls other method.
    return addColumn(allElements);
  }

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
    //Asserts that the given elements are not null.
    Validator.assertThatTheElements(elements).areNotNull();

    final var lElements = ContainerView.forIterable(elements);

    //Handles the case that the current {@link Matrix} is empty.
    if (isEmpty()) {
      if (lElements.containsAny()) {
        memberElements = new Object[lElements.getCount()][1];

        //Iterates the given elements.
        var i = 0;
        for (final var e : lElements) {
          memberElements[i][0] = e;

          i++;
        }
      }

      //Handles the case that the current matrix is not empty.
    } else {
      //Asserts that as many elements are given as the number of rows of the current
      //matrix.
      Validator
        .assertThat(lElements.getCount())
        .thatIsNamed("number of the given elements")
        .isEqualTo(getRowCount());

      final var columnCount = getColumnCount();

      //Iterates the given elements.
      var i = 0;
      for (final var e : lElements) {
        final var row = Arrays.copyOf(memberElements[i], columnCount + 1);
        row[columnCount] = e;

        memberElements[i] = row;

        i++;
      }
    }

    return this;
  }

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
  @SuppressWarnings("unchecked")
  public Matrix<E> addRow(final E... elements) {
    //Creates elementsContainerView.
    final var elementsContainerView = ContainerView.forArray(elements);

    //Calls other method.
    return addRow(elementsContainerView);
  }

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
    //Asserts that the given elements are not null.
    Validator.assertThatTheElements(elements).areNotNull();

    final var lElements = ContainerView.forIterable(elements);

    //Handles the case that the current matrix is empty.
    if (isEmpty()) {
      if (lElements.containsAny()) {
        memberElements = new Object[1][lElements.getCount()];

        //Iterates the given elements.
        var i = 0;
        for (final var e : lElements) {
          memberElements[0][i] = e;

          i++;
        }
      }

      //Handles the case that the current matrix is not empty.
    } else {
      //Asserts that as many elements are given as the number of columns of the
      //current matrix.
      Validator
        .assertThat(lElements.getCount())
        .thatIsNamed("number of the given elements")
        .isEqualTo(getColumnCount());

      final var rowCount = getRowCount();
      final var newElements = Arrays.copyOf(memberElements, rowCount + 1);
      newElements[rowCount] = new Object[getColumnCount()];

      //Iterates the given elements.
      var i = 0;
      for (final var e : lElements) {
        newElements[rowCount][i] = e;

        i++;
      }

      memberElements = newElements;
    }

    return this;
  }

  /**
   * Removes all elements of the current {@link Matrix}. The complexity of this
   * implementation is O(1).
   */
  @Override
  public void clear() {
    memberElements = new Object[0][0];
  }

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

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return the number of columns of the current {@link Matrix}.
   */
  @Override
  public int getColumnCount() {
    //Handles the case that the current {@link Matrix} is empty.
    if (memberElements.length < 1) {
      return 0;
    }

    //Handles the case that the current {@link Matrix} is not empty.
    return memberElements[0].length;
  }

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
    //Asserts that the current matrix contains an element at the given index.
    assertContainsAt(index);

    final var columnCount = getColumnCount();

    if (columnCount == 0) {
      throw EmptyArgumentException.forArgument(this);
    }

    return ((index - 1) % columnCount + 1);
  }

  /**
   * @return the columns of the current {@link Matrix}.
   */
  @Override
  public IContainer<MatrixColumn<E>> getColumns() {
    final ILinkedList<MatrixColumn<E>> columns = LinkedList.createEmpty();

    //Iterates the columns of the current matrix.
    for (var i = 1; i <= getColumnCount(); i++) {
      columns.addAtEnd(new MatrixColumn<>(this, i));
    }

    return columns;
  }

  /**
   * The time complexity of this implementation is O(m * n) if: -This matrix
   * contains m rows. -This matrix contains n columns.
   * 
   * @return a new {@link Matrix} with the elements of the current {@link Matrix}.
   */
  public Matrix<E> getCopy() {
    final var matrix = new Matrix<E>();

    final var rowCounnt = getRowCount();
    final var columnCount = getColumnCount();

    matrix.memberElements = new Object[rowCounnt][columnCount];
    for (var i = 0; i < rowCounnt; i++) {
      matrix.memberElements[i] = Arrays.copyOf(memberElements[i], columnCount);
    }

    return matrix;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return the number of elements of the current {@link Matrix}.
   */
  @Override
  public int getCount() {
    return (getRowCount() * getColumnCount());
  }

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
    //Asserts that the current matrix contains an element
    //at the given row index and the given column index.
    assertContainsAt(rowIndex, columnIndex);

    return ((rowIndex - 1) * getColumnCount() + columnIndex);
  }

  /**
   * @return the element of the current {@link Matrix} at the given index .
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return getStoredAtOneBasedRowIndexAndColumnIndex(getRowIndexOf(oneBasedIndex), getColumnIndexOf(oneBasedIndex));
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
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
  public E getStoredAtOneBasedRowIndexAndColumnIndex(final int oneBasedRowIndex, final int oneBasedColumnIndex) {
    //Asserts that the current matrix contains an element at the given row index
    //and column index.
    assertContainsAt(oneBasedRowIndex, oneBasedColumnIndex);

    return (E) memberElements[oneBasedRowIndex - 1][oneBasedColumnIndex - 1];
  }

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
    //Asserts that the current matrix contains an element at the given index.
    assertContainsAt(index);

    final var columnCount = getColumnCount();

    if (columnCount == 0) {
      throw EmptyArgumentException.forArgument(this);
    }

    return ((index - 1) / columnCount + 1);
  }

  /**
   * @return the rows of the current {@link Matrix}.
   */
  @Override
  public IContainer<MatrixRow<E>> getRows() {
    final ILinkedList<MatrixRow<E>> rows = LinkedList.createEmpty();

    //Iterates the rows of the current matrix.
    for (var i = 1; i <= getRowCount(); i++) {
      rows.addAtEnd(new MatrixRow<>(this, i));
    }

    return rows;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return the number of rows of the current {@link Matrix}.
   */
  @Override
  public int getRowCount() {
    //Handles the case that the current {@link Matrix} is empty.
    if (memberElements.length < 1) {
      return 0;
    }

    //Handles the case that the current {@link Matrix} is not empty.
    return memberElements.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return a new iterator for the current {@link Matrix}.
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MatrixIterator.forMatrix(this);
  }

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
    //Asserts that the given element is not null.
    Validator
      .assertThat(element)
      .thatIsNamed(LowerCaseVariableCatalog.ELEMENT)
      .isNotNull();

    //Sets the given element at the given index to the current matrix.
    memberElements[getRowIndexOf(index) - 1][getColumnIndexOf(index) - 1] = element;
  }

  /**
   * Sets the given element to the current {@link Matrix} to the row with the
   * given row index and the column with the given column index.
   * 
   * The time complexity of this implementation is O(1).
   * 
   * @param oneBasedRowIndex
   * @param oneBasedColumnIndex
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
  public void setAtOneBasedRowIndexAndColumnIndex(
    final int oneBasedRowIndex,
    final int oneBasedColumnIndex,
    final E element) {
    //Asserts that the current matrix contains an element at the given row index
    //and column index.
    assertContainsAt(oneBasedRowIndex, oneBasedColumnIndex);

    //Asserts that the given element is not null.
    Validator
      .assertThat(element)
      .thatIsNamed(LowerCaseVariableCatalog.ELEMENT)
      .isNotNull();

    memberElements[oneBasedRowIndex - 1][oneBasedColumnIndex - 1] = element;
  }

  /**
   * The time complexity of this implementation is O(n) if: -This matrix contains
   * n elements. -The given transformer has a complexity of O(1).
   * 
   * @param transformer
   * @param <O>         is the type of the elements the given transformer returns.
   * @return a new matrix with the elements the given transformer transforms of
   *         the elements of the current {@link Matrix}.
   */
  @SuppressWarnings("unchecked")
  public <O> Matrix<O> toMatrix(final Function<E, O> transformer) {
    //Creates matrix.
    final var matrix = new Matrix<O>();

    //Fills up the elements of the matrix.
    matrix.memberElements = new Object[getRowCount()][getColumnCount()];
    for (var i = 0; i < getRowCount(); i++) {
      //Iterates the columns of the current row.
      for (var j = 0; j < getColumnCount(); j++) {
        matrix.memberElements[i][j] = transformer.apply((E) memberElements[i][j]);
      }
    }

    return matrix;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Matrix} contains n elements.
   * 
   * @return a new left rotated {@link Matrix} of the current {@link Matrix}.
   */
  public Matrix<E> toLeftRotatedMatrix() {
    final var leftRotatedMatrix = new Matrix<E>();
    final var leftRotatedMatrixRowCount = getColumnCount();
    final var leftRotatedMatrixColumnCount = getRowCount();

    final var leftRotatedMatrixElements = new Object[leftRotatedMatrixRowCount][leftRotatedMatrixColumnCount];

    leftRotatedMatrix.memberElements = leftRotatedMatrixElements;

    //Iterates the rows of the left rotated matrix.
    for (var i = 0; i < leftRotatedMatrixRowCount; i++) {
      //Iterates the columns of the current row.
      for (var j = 0; j < leftRotatedMatrixColumnCount; j++) {
        leftRotatedMatrixElements[i][j] = memberElements[j][leftRotatedMatrixRowCount - i - 1];
      }
    }

    return leftRotatedMatrix;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Matrix} contains n elements.
   * 
   * @return a new right rotated {@link Matrix} of the current {@link Matrix}.
   */
  public Matrix<E> toRightRotatedMatrix() {
    final var rightRotatedMatrix = new Matrix<E>();
    final var rightRotatedMatrixRowCount = getColumnCount();
    final var rightRotatedMatrixColumnCount = getRowCount();

    final var rightRotatedMatrixElements = new Object[rightRotatedMatrixRowCount][rightRotatedMatrixColumnCount];

    rightRotatedMatrix.memberElements = rightRotatedMatrixElements;

    //Iterates the rows of the right rotated matrix.
    for (var i = 0; i < rightRotatedMatrixRowCount; i++) {
      //Iterates the columns of the current row.
      for (var j = 0; j < rightRotatedMatrixColumnCount; j++) {
        rightRotatedMatrixElements[i][j] = memberElements[rightRotatedMatrixColumnCount - j - 1][i];
      }
    }

    return rightRotatedMatrix;
  }

  /**
   * The time complexity of this implementation is O(n) if: -The current
   * {@link Matrix} contains n elements. -The toString method of the elements of
   * the current {@link Matrix} has a complexity of O(1).
   * 
   * @return a {@link String} representation of the current {@link Matrix}.
   */
  @Override
  public String toString() {
    final var stringBuilder = new StringBuilder();

    //Iterates the rows of the current matrix.
    for (var i = 0; i < getRowCount(); i++) {
      if (i > 0) {
        stringBuilder.append(CharacterCatalog.SEMICOLON);
      }

      //Iterates the columns of the current row.
      for (var j = 0; j < getColumnCount(); j++) {
        if (j > 0) {
          stringBuilder.append(CharacterCatalog.COMMA);
        }

        stringBuilder.append(memberElements[i][j].toString());
      }
    }

    return stringBuilder.toString();
  }

  /**
   * @param index
   * @throws NonPositiveArgumentException if the given index is not positive.
   * @throws BiggerArgumentException      if the given index is bigger than the
   *                                      number of elements of the current
   *                                      {@link Matrix}.
   */
  private void assertContainsAt(final int index) {
    Validator
      .assertThat(index)
      .thatIsNamed(LowerCaseVariableCatalog.INDEX)
      .isPositive();

    Validator
      .assertThat(index)
      .thatIsNamed(LowerCaseVariableCatalog.INDEX)
      .isNotBiggerThan(getCount());
  }

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
    Validator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseVariableCatalog.ROW_INDEX)
      .isPositive();

    Validator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseVariableCatalog.ROW_INDEX)
      .isNotBiggerThan(getRowCount());

    Validator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseVariableCatalog.COLUMN_INDEX)
      .isPositive();

    Validator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseVariableCatalog.COLUMN_INDEX)
      .isNotBiggerThan(getColumnCount());
  }
}
