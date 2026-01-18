package ch.nolix.core.container.matrix;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link Matrix} of a
 *            {@link MatrixRow}.
 */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return parentMatrix.getColumnCount();
  }

  public int getRowIndex() {
    return rowIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int columnIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(getRowIndex(), columnIndex);
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
    return MatrixRowIterator.forMatrixRow(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
