package ch.nolix.core.container.matrix;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class MatrixColumn<E> extends Container<E> {

  private final Matrix<E> parentMatrix;

  private final int columnIndex;

  MatrixColumn(final Matrix<E> parentMatrix, final int columnIndex) {

    GlobalValidator
      .assertThat(parentMatrix)
      .thatIsNamed("parent matrix")
      .isNotNull();

    GlobalValidator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.COLUMN_INDEX)
      .isPositive();

    this.parentMatrix = parentMatrix;
    this.columnIndex = columnIndex;
  }

  @Override
  public int getCount() {
    return parentMatrix.getRowCount();
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  @Override
  public E getStoredAt1BasedIndex(final int rowIndex) {
    return parentMatrix.getStoredAt1BasedRowIndexAndColumnIndex(rowIndex, getColumnIndex());
  }

  @Override
  public boolean isMaterialized() {
    return false;
  }

  @Override
  public CopyableIterator<E> iterator() {
    return MatrixColumnIterator.forMatrixColumn(this);
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
}
