package ch.nolix.core.container.linkedlist;

import java.util.function.Function;

import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetoolapi.iterabletoolapi.IIterableTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-05-14
 * @param <E> is the type of the elements of a {@link MappingContainerView}.
 * @param <T> is the type of the elements a {@link MappingContainerView} maps
 *            from its elements.
 */
public final class MappingContainerView<E, T> extends AbstractExtendedContainer<T> {

  private static final IIterableTool ITERABLE_TOOL = new IterableTool();

  private final IContainer<E> container;

  private final Function<E, T> mapper;

  /**
   * Creates a new {@link MappingContainerView} for the given container and
   * mapper.
   * 
   * @param container
   * @param mapper
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if the given mapper is null.
   */
  private MappingContainerView(final IContainer<E> container, final Function<E, T> mapper) {

    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();

    this.container = container;
    this.mapper = mapper;
  }

  /**
   * @param container
   * @param mapper
   * @param <E2>      is the type of the elements of the created
   *                  {@link MappingContainerView}.
   * @param <T2>      is the type of the elements the created
   *                  {@link MappingContainerView} maps from its elements.
   * @return a new {@link MappingContainerView} with the given container and
   *         mapper.
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if the given mapper is null.
   */
  public static <E2, T2> MappingContainerView<E2, T2> forContainerAndMapper(
    final AbstractContainer<E2> container,
    final Function<E2, T2> mapper) {
    return new MappingContainerView<>(container, mapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return ITERABLE_TOOL.getCount(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T getStoredAtOneBasedIndex(final int oneBasedIndex) {

    final var element = container.getStoredAtOneBasedIndex(oneBasedIndex);

    return mapper.apply(element);
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
  public CopyableIterator<T> iterator() {
    return MappingContainerViewIterator.forIteratorAndMapper(container.iterator(), mapper);
  }
}
