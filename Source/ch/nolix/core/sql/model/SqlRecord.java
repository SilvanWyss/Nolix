package ch.nolix.core.sql.model;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.coreapi.sql.model.ISqlRecord;

/**
 * A {@link SqlRecord} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public final class SqlRecord extends AbstractExtendedContainer<String> implements ISqlRecord {
  private final int oneBasedIndex;

  private final ImmutableList<String> values;

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Creates a new {@link SqlRecord} with the given values.
   * 
   * @param oneBasedIndex
   * @param values
   * @throws NonPositiveArgumentException if the given oneBasedIndex is not
   *                                      positive.
   * @throws ArgumentIsNullException      if the given values is null.
   */
  private SqlRecord(final int oneBasedIndex, final ImmutableList<String> values) {
    if (oneBasedIndex < 1) {
      throw //
      NonPositiveArgumentException.forArgumentAndArgumentName(oneBasedIndex, LowerCaseVariableCatalog.ONE_BASED_INDEX);
    }

    if (values == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalog.VALUES);
    }

    this.oneBasedIndex = oneBasedIndex;
    this.values = values;
  }

  /**
   * @param oneBasedIndex
   * @param value
   * @return a new {@link SqlRecord} with the given oneBasedIndex and value.
   * @throws NonPositiveArgumentException if the given oneBasedIndex is not
   *                                      positive.
   * @throws ArgumentIsNullException      if the given value is null.
   */
  public static SqlRecord withOneBasedIndexAndValue(final int oneBasedIndex, final String value) {
    final var values = ImmutableList.withElements(value);

    return withOneBasedIndexAndValues(oneBasedIndex, values);
  }

  /**
   * @param oneBasedIndex
   * @param values
   * @return a new {@link SqlRecord} with the oneBasedIndex and given values.
   * @throws NonPositiveArgumentException if the given oneBasedIndex is not
   *                                      positive.
   * @throws ArgumentIsNullException      if the given values is null.
   * @throws ArgumentIsNullException      if one of the given values is null.
   */
  public static SqlRecord withOneBasedIndexAndValues(final int oneBasedIndex, final Iterable<String> values) {
    return new SqlRecord(oneBasedIndex, ImmutableList.fromIterable(values));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return values.getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getOneBasedIndex() {
    return oneBasedIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return values.getStoredAtOneBasedIndex(oneBasedIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<String> iterator() {
    return values.iterator();
  }
}
