package ch.nolix.core.container.matrix;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link Matrix} of a
 *            {@link MatrixColumn}.
 */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return parentMatrix.getRowCount();
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int rowIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(rowIndex, getColumnIndex());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MatrixColumnIterator.forMatrixColumn(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
