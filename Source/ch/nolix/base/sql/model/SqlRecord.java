/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.model;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;
import ch.nolix.baseapi.sql.model.ISqlRecord;

/**
 * A {@link SqlRecord} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class SqlRecord extends AbstractExtendedIterable<String> implements ISqlRecord {
  private final int memberOneBasedIndex;

  private final ImmutableList<String> values;

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Creates a new {@link SqlRecord} with the given values.
   * 
   * @param oneBasedIndex
   * @param values
   * @throws RuntimeException if the given oneBasedIndex is not positive
   * @throws RuntimeException if the given values is null
   */
  private SqlRecord(final int oneBasedIndex, final ImmutableList<String> values) {
    if (oneBasedIndex < 1) {
      throw //
      NonPositiveArgumentException.forArgumentAndArgumentName(
        oneBasedIndex,
        LowerCaseVariableNameCatalog.ONE_BASED_INDEX);
    }

    if (values == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableNameCatalog.VALUES);
    }

    memberOneBasedIndex = oneBasedIndex;
    this.values = values;
  }

  /**
   * @param oneBasedIndex
   * @param value
   * @return a new {@link SqlRecord} with the given oneBasedIndex and value
   * @throws RuntimeException if the given oneBasedIndex is not positive
   * @throws RuntimeException if the given value is null
   */
  public static SqlRecord withOneBasedIndexAndValue(final int oneBasedIndex, final String value) {
    final var values = ImmutableList.withElements(value);

    return withOneBasedIndexAndValues(oneBasedIndex, values);
  }

  /**
   * @param oneBasedIndex
   * @param values
   * @return a new {@link SqlRecord} with the oneBasedIndex and given values
   * @throws RuntimeException if the given oneBasedIndex is not positive
   * @throws RuntimeException if the given values is null
   * @throws RuntimeException if one of the given values is null
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
    return memberOneBasedIndex;
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

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return ArrayList.withInitialCapacity(initialCapacity);
  }
}
