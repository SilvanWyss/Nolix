package ch.nolix.core.sql.model;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;
import ch.nolix.coreapi.sqlapi.modelapi.IRecord;

/**
 * A {@link Record} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public final class Record extends Container<String> implements IRecord {

  private final ImmutableList<String> values;

  /**
   * Creates a new {@link Record} with the given values.
   * 
   * @param values
   * @throws ArgumentIsNullException if the given values is null.
   */
  private Record(final ImmutableList<String> values) {

    GlobalValidator.assertThat(values).thatIsNamed(PluralLowerCaseVariableCatalogue.VALUES).isNotNull();

    this.values = values;
  }

  /**
   * @param values
   * @return a new {@link Record} with the given values.
   * @throws ArgumentIsNullException if the given values is null.
   */
  public static Record withValues(final IArrayList<String> values) {
    return new Record(ImmutableList.forIterable(values));
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
