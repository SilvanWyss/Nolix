package ch.nolix.core.container.matrix;

import java.util.function.Function;

import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.containerview.IntervallContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class MatrixRow<E> extends AbstractContainer<E> {

  private final Matrix<E> parentMatrix;

  private final int rowIndex;

  MatrixRow(final Matrix<E> parentMatrix, final int rowIndex) {

    Validator
      .assertThat(parentMatrix)
      .thatIsNamed("parent matrix")
      .isNotNull();

    Validator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseVariableCatalog.ROW_INDEX)
      .isPositive();

    this.parentMatrix = parentMatrix;
    this.rowIndex = rowIndex;
  }

  @Override
  public int getCount() {
    return parentMatrix.getColumnCount();
  }

  public int getRowIndex() {
    return rowIndex;
  }

  @Override
  public E getStoredAtOneBasedIndex(final int columnIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(getRowIndex(), columnIndex);
  }

  @Override
  public IContainer<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(
    final int oneBasedStartIndex,
    final int oneBasedEndIndex) {
    return IntervallContainerView.forContainerAndStartIndexAndEndIndex(this, oneBasedStartIndex, oneBasedEndIndex);
  }

  @Override
  public boolean isMaterialized() {
    return false;
  }

  @Override
  public CopyableIterator<E> iterator() {
    return MatrixRowIterator.forMatrixRow(this);
  }

  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }

  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
