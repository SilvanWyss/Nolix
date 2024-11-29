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

public final class MatrixRow<E> extends Container<E> {

  private final Matrix<E> parentMatrix;

  private final int rowIndex;

  MatrixRow(final Matrix<E> parentMatrix, final int rowIndex) {

    GlobalValidator
      .assertThat(parentMatrix)
      .thatIsNamed("parent matrix")
      .isNotNull();

    GlobalValidator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.ROW_INDEX)
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
  public E getStoredAt1BasedIndex(final int columnIndex) {
    return parentMatrix.getStoredAt1BasedRowIndexAndColumnIndex(getRowIndex(), columnIndex);
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
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
