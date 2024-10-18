package ch.nolix.core.container.gapmatrix;

import java.util.Arrays;
import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public final class GapMatrix<E> extends Container<E> implements Clearable {

  private int columnCount;

  private int elementCount;

  private Object[][] rows = new Object[0][0];

  public GapMatrix() {
  }

  public GapMatrix(final int rowCount, final int columnCount) {

    GlobalValidator.assertThat(rowCount).thatIsNamed(LowerCaseVariableCatalogue.ROW_COUNT).isNotNegative();
    GlobalValidator.assertThat(columnCount).thatIsNamed(LowerCaseVariableCatalogue.COLUMN_COUNT).isNotNegative();

    rows = new Object[rowCount][columnCount];
    this.columnCount = columnCount;
  }

  public void addColumn() {

    final var rowCount = getRowCount();

    for (var i = 0; i < rowCount; i++) {
      rows[i] = Arrays.copyOf(rows[i], columnCount + 1);
    }

    columnCount++;
  }

  public void addRow() {

    final var newRowCount = getRowCount() + 1;

    rows = Arrays.copyOf(rows, newRowCount);
    rows[newRowCount - 1] = new Object[getColumnCount()];
  }

  @Override
  public void clear() {
    rows = new Object[0][];
    columnCount = 0;
    elementCount = 0;
  }

  public boolean containsAt1BasedRowIndexAndColumnIndex(final int param1BasedRowIndex,
    final int param1BasedColumnIndex) {
    return containsCellAt1BasedRowIndexAndColumnIndex(param1BasedRowIndex, param1BasedColumnIndex)
    && rows[param1BasedRowIndex - 1][param1BasedColumnIndex - 1] != null;
  }

  public boolean containsCellAt1BasedRowIndexAndColumnIndex(final int param1BasedRowIndex,
    final int param1BasedColumnIndex) {
    return param1BasedRowIndex > 0
    && param1BasedRowIndex <= getRowCount()
    && param1BasedColumnIndex > 0
    && param1BasedColumnIndex <= getColumnCount();
  }

  public int getColumnCount() {
    return columnCount;
  }

  public int getColumnIndexOf(final int index) {

    return ((index - 1) % getColumnCount() + 1);
  }

  @Override
  public int getCount() {
    return elementCount;
  }

  @Override
  public E getStoredAt1BasedIndex(final int param1BasedIndex) {
    return getStoredAt1BasedRowIndexAndColumnIndex(getRowIndexOf(param1BasedIndex), getColumnIndexOf(param1BasedIndex));
  }

  @SuppressWarnings("unchecked")
  public E getStoredAt1BasedRowIndexAndColumnIndex(final int param1BasedRowIndex, final int param1BasedColumnIndex) {

    assertContainsAt(param1BasedRowIndex, param1BasedColumnIndex);

    return (E) rows[param1BasedRowIndex - 1][param1BasedColumnIndex - 1];
  }

  //For a better performance, this implementation does not use all comfortable methods.
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

  public int getRowCount() {
    return rows.length;
  }

  public int getRowIndexOf(final int index) {

    return ((index - 1) / getColumnCount() + 1);
  }

  public int getSize() {
    return (getRowCount() * getColumnCount());
  }

  public void setAt1BasedRowIndexAndColumnIndex(
    final int param1BasedRowIndex,
    final int param1BasedColumnIndex,
    final E element) {

    GlobalValidator.assertThat(element).thatIsNamed(LowerCaseVariableCatalogue.ELEMENT).isNotNull();

    assertCanContainElementAt(param1BasedRowIndex, param1BasedColumnIndex);

    if (!containsAt1BasedRowIndexAndColumnIndex(param1BasedRowIndex, param1BasedColumnIndex)) {
      elementCount++;
    }

    rows[param1BasedRowIndex - 1][param1BasedColumnIndex - 1] = element;
  }

  @Override
  public boolean isMaterialized() {
    return true;
  }

  @Override
  public CopyableIterator<E> iterator() {
    return GapMatrixIterator.forGapMatrix(this);
  }

  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }

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

  private void assertContainsAt(final int rowIndex, final int columnIndex) {

    assertCanContainElementAt(rowIndex, columnIndex);

    if (rows[rowIndex - 1][columnIndex - 1] == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        "(" + rowIndex + "," + columnIndex + ")",
        "is no position of an element");
    }
  }
}
