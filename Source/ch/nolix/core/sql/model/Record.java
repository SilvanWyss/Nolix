package ch.nolix.core.sql.model;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;
import ch.nolix.coreapi.sqlapi.modelapi.IRecord;

/**
 * A {@link Record} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public final class Record extends Container<String> implements IRecord {

  private final int oneBasedIndex;

  private final ImmutableList<String> values;

  //TODO: Say available comfort methods instead of comfortable methods 
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * Creates a new {@link Record} with the given values.
   * 
   * @param oneBasedIndex
   * @param values
   * @throws NonPositiveArgumentException if the given oneBasedIndex is not
   *                                      positive.
   * @throws ArgumentIsNullException      if the given values is null.
   */
  private Record(final int oneBasedIndex, final ImmutableList<String> values) {

    if (oneBasedIndex < 1) {

      //TODO: Rename Catalogues to Catalogs
      throw //
      NonPositiveArgumentException.forArgumentNameAndArgument(
        LowerCaseVariableCatalogue.ONE_BASED_INDEX,
        oneBasedIndex);
    }

    if (values == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalogue.VALUES);
    }

    this.oneBasedIndex = oneBasedIndex;
    this.values = values;
  }

  /**
   * @param oneBasedIndex
   * @param value
   * @return a new {@link Record} with the given oneBasedIndex and value.
   * @throws NonPositiveArgumentException if the given oneBasedIndex is not
   *                                      positive.
   * @throws ArgumentIsNullException      if the given value is null.
   */
  public static Record withOneBasedIndexAndValue(final int oneBasedIndex, final String value) {

    final var values = ImmutableList.withElement(value);

    return withOneBasedIndexAndValues(oneBasedIndex, values);
  }

  /**
   * @param oneBasedIndex
   * @param values
   * @return a new {@link Record} with the oneBasedIndex and given values.
   * @throws NonPositiveArgumentException if the given oneBasedIndex is not
   *                                      positive.
   * @throws ArgumentIsNullException      if the given values is null.
   * @throws ArgumentIsNullException      if one of the given values is null.
   */
  public static Record withOneBasedIndexAndValues(final int oneBasedIndex, final Iterable<String> values) {
    return new Record(oneBasedIndex, ImmutableList.forIterable(values));
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
  public String getStoredAt1BasedIndex(final int param1BasedIndex) {
    return values.getStoredAt1BasedIndex(param1BasedIndex);
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
  public <C extends Comparable<C>> IContainer<String> toOrderedList(final Function<String, C> comparableMapper) {
    return values.toOrderedList(comparableMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
