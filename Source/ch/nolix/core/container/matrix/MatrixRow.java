package ch.nolix.core.container.matrix;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class MatrixRow<E> extends AbstractExtendedContainer<E> {

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
  public boolean isMaterialized() {
    return false;
  }

  @Override
  public CopyableIterator<E> iterator() {
    return MatrixRowIterator.forMatrixRow(this);
  }

  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
