package ch.nolix.core.container.matrix;

import ch.nolix.core.container.linkedlist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class MatrixColumn<E> extends AbstractExtendedContainer<E> {

  private final Matrix<E> parentMatrix;

  private final int columnIndex;

  MatrixColumn(final Matrix<E> parentMatrix, final int columnIndex) {

    Validator
      .assertThat(parentMatrix)
      .thatIsNamed("parent matrix")
      .isNotNull();

    Validator
      .assertThat(columnIndex)
      .thatIsNamed(LowerCaseVariableCatalog.COLUMN_INDEX)
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
  public E getStoredAtOneBasedIndex(final int rowIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(rowIndex, getColumnIndex());
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
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
