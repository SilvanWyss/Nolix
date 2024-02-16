//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.Arrays;
import java.util.function.Function;

//own imports
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.generalstateapi.statemutationapi.Clearable;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class GapMatrix<E> extends Container<E> implements Clearable {

  //attribute
  private int columnCount;

  //attribute
  private int elementCount;

  //multi-attribute
  private Object[][] rows = new Object[0][0];

  //constructor
  public GapMatrix() {
  }

  //constructor
  public GapMatrix(final int rowCount, final int columnCount) {

    GlobalValidator.assertThat(rowCount).thatIsNamed(LowerCaseVariableCatalogue.ROW_COUNT).isNotNegative();
    GlobalValidator.assertThat(columnCount).thatIsNamed(LowerCaseVariableCatalogue.COLUMN_COUNT).isNotNegative();

    rows = new Object[rowCount][columnCount];
    this.columnCount = columnCount;
  }

  //method
  public void addColumn() {

    final var rowCount = getRowCount();

    for (var i = 0; i < rowCount; i++) {
      rows[i] = Arrays.copyOf(rows[i], columnCount + 1);
    }

    columnCount++;
  }

  //method
  public void addRow() {

    final var newRowCount = getRowCount() + 1;

    rows = Arrays.copyOf(rows, newRowCount);
    rows[newRowCount - 1] = new Object[getColumnCount()];
  }

  //method
  @Override
  public void clear() {
    rows = new Object[0][];
    columnCount = 0;
    elementCount = 0;
  }

  //method
  public boolean containsAt1BasedRowIndexAndColumnIndex(final int p1BasedRowIndex, final int p1BasedColumnIndex) {
    return containsCellAt1BasedRowIndexAndColumnIndex(p1BasedRowIndex, p1BasedColumnIndex)
    && rows[p1BasedRowIndex - 1][p1BasedColumnIndex - 1] != null;
  }

  //method
  public boolean containsCellAt1BasedRowIndexAndColumnIndex(final int p1BasedRowIndex, final int p1BasedColumnIndex) {
    return p1BasedRowIndex > 0
    && p1BasedRowIndex <= getRowCount()
    && p1BasedColumnIndex > 0
    && p1BasedColumnIndex <= getColumnCount();
  }

  //method
  public int getColumnCount() {
    return columnCount;
  }

  //method
  public int getColumnIndexOf(final int index) {

    return ((index - 1) % getColumnCount() + 1);
  }

  //method
  @Override
  public int getElementCount() {
    return elementCount;
  }

  //method
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {
    return getStoredAt1BasedRowIndexAndColumnIndex(getRowIndexOf(p1BasedIndex), getColumnIndexOf(p1BasedIndex));
  }

  //method
  @SuppressWarnings("unchecked")
  public E getStoredAt1BasedRowIndexAndColumnIndex(final int p1BasedRowIndex, final int p1BasedColumnIndex) {

    assertContainsAt(p1BasedRowIndex, p1BasedColumnIndex);

    return (E) rows[p1BasedRowIndex - 1][p1BasedColumnIndex - 1];
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public E getStoredLast() {

    final var rowCount = getRowCount();

    for (var rowIndex = rowCount - 1; rowIndex >= 0; rowIndex--) {
      for (var columnIndex = columnCount - 1; columnIndex >= 0; columnIndex--) {
        if (rows[rowIndex][columnIndex] != null) {
          return (E) rows[rowIndex][columnIndex];
        }
      }
    }

    throw EmptyArgumentException.forArgument(this);
  }

  //method
  public int getRowCount() {
    return rows.length;
  }

  public int getRowIndexOf(final int index) {

    return ((index - 1) / getColumnCount() + 1);
  }

  //method
  public int getSize() {
    return (getRowCount() * getColumnCount());
  }

  //method
  public void setAt1BasedRowIndexAndColumnIndex(
    final int p1BasedRowIndex,
    final int p1BasedColumnIndex,
    final E element) {

    GlobalValidator.assertThat(element).thatIsNamed(LowerCaseVariableCatalogue.ELEMENT).isNotNull();

    assertCanContainElementAt(p1BasedRowIndex, p1BasedColumnIndex);

    if (!containsAt1BasedRowIndexAndColumnIndex(p1BasedRowIndex, p1BasedColumnIndex)) {
      elementCount++;
    }

    rows[p1BasedRowIndex - 1][p1BasedColumnIndex - 1] = element;
  }

  //method
  @Override
  public boolean isMaterialized() {
    return true;
  }

  //method
  @Override
  public CopyableIterator<E> iterator() {
    return GapMatrixIterator.forGapMatrix(this);
  }

  //method
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  //method
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return new LinkedList<>();
  }

  //method
  private void assertCanContainElementAt(final int rowIndex, final int columnIndex) {

    GlobalValidator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.ROW_INDEX)
      .isPositive();

    GlobalValidator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.ROW_INDEX)
      .isNotBiggerThan(getRowCount());

    GlobalValidator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.COLUMN_INDEX)
      .isPositive();

    GlobalValidator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.COLUMN_INDEX)
      .isNotBiggerThan(getColumnCount());
  }

  //method
  private void assertContainsAt(final int rowIndex, final int columnIndex) {

    assertCanContainElementAt(rowIndex, columnIndex);

    if (rows[rowIndex - 1][columnIndex - 1] == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        "(" + rowIndex + "," + columnIndex + ")",
        "is no position of an element");
    }
  }
}
