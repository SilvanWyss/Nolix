/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.matrix;

import ch.nolix.base.container.arraylist.AbstractExtendedContainer;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.container.matrix.IMatrixColumn;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link Matrix} of a
 *            {@link MatrixColumn}.
 */
public final class MatrixColumn<E> extends AbstractExtendedContainer<E> implements IMatrixColumn<E> {
  private final Matrix<E> parentMatrix;

  private final int oneBasedColumnIndex;

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
    this.oneBasedColumnIndex = columnIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return parentMatrix.getRowCount();
  }

  @Override
  public int getOneBasedColumnIndex() {
    return oneBasedColumnIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int rowIndex) {
    return parentMatrix.getStoredAtOneBasedRowIndexAndColumnIndex(rowIndex, getOneBasedColumnIndex());
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
