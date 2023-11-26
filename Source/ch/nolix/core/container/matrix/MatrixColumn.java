//package declaration
package ch.nolix.core.container.matrix;

//Java imports
import java.util.function.Function;

//own imports
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
public final class MatrixColumn<E> extends Container<E> {

  //attribute
  private final Matrix<E> parentMatrix;

  //attribute
  private final int columnIndex;

  //constructor
  MatrixColumn(final Matrix<E> parentMatrix, final int columnIndex) {

    GlobalValidator
      .assertThat(parentMatrix)
      .thatIsNamed("parent matrix")
      .isNotNull();

    GlobalValidator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
      .isPositive();

    this.parentMatrix = parentMatrix;
    this.columnIndex = columnIndex;
  }

  //method
  @Override
  public int getElementCount() {
    return parentMatrix.getRowCount();
  }

  //method
  public int getColumnIndex() {
    return columnIndex;
  }

  //method
  @Override
  public E getStoredAt1BasedIndex(final int rowIndex) {
    return parentMatrix.getStoredAt1BasedRowIndexAndColumnIndex(rowIndex, getColumnIndex());
  }

  //method
  @Override
  public E getStoredLast() {
    return parentMatrix.getStoredAt1BasedRowIndexAndColumnIndex(parentMatrix.getRowCount(), getColumnIndex());
  }

  //method
  @Override
  public boolean isMaterialized() {
    return false;
  }

  //method
  @Override
  public CopyableIterator<E> iterator() {
    return MatrixColumnIterator.forMatrixColumn(this);
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
