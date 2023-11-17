//package declaration
package ch.nolix.core.container.matrix;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypeapi.stringutilapi.CharacterCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public final class MatrixRow<E> extends Container<E> {

  //attribute
  private final Matrix<E> parentMatrix;

  //attribute
  private final int rowIndex;

  //constructor
  MatrixRow(final Matrix<E> parentMatrix, final int rowIndex) {

    GlobalValidator
      .assertThat(parentMatrix)
      .thatIsNamed("parent matrix")
      .isNotNull();

    GlobalValidator
      .assertThat(rowIndex)
      .thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
      .isPositive();

    this.parentMatrix = parentMatrix;
    this.rowIndex = rowIndex;
  }

  //method
  @Override
  public int getElementCount() {
    return parentMatrix.getColumnCount();
  }

  //method
  public int getRowIndex() {
    return rowIndex;
  }

  //method
  @Override
  public E getStoredAt1BasedIndex(final int columnIndex) {
    return parentMatrix.getStoredAt1BasedRowIndexAndColumnIndex(getRowIndex(), columnIndex);
  }

  //method
  @Override
  public E getStoredLast() {
    return parentMatrix.getStoredAt1BasedRowIndexAndColumnIndex(getRowIndex(), parentMatrix.getColumnCount());
  }

  //method
  @Override
  public boolean isMaterialized() {
    return false;
  }

  //method
  @Override
  public CopyableIterator<E> iterator() {
    return MatrixRowIterator.forMatrixRow(this);
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
}
