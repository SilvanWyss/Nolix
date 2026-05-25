/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.value;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.property.value.IMultiValue;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link MultiValue}.
 */
public final class MultiValue<V> extends AbstractValue<V> implements IMultiValue<V> {
  private final LinkedList<V> values = LinkedList.createEmpty();

  private final Consumer<V> adder;

  /**
   * Creates a new {@link MultiValue} with the given name, adder, valueMapper and
   * specificationMapper.
   * 
   * @param name
   * @param valueMapper
   * @param adder
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given adder is null.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the given specificationMapper is null.
   */
  private MultiValue(
    final String name,
    final Consumer<V> adder,
    final Function<INode<?>, V> valueMapper,
    final Function<V, INode<?>> specificationMapper) {
    super(name, valueMapper, specificationMapper);

    Validator.assertThat(adder).thatIsNamed(LowerCaseVariableCatalog.ADDER).isNotNull();

    this.adder = adder;
  }

  /**
   * @param name
   * @param adder
   * @param valueMapper
   * @param <E>         the type of the values of a {@link MultiValue}
   * @return a new {@link MultiValue} with the given name, adder and valueMapper
   *         that can store {@link IElement}s that can be of different sub types
   *         of <E>
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given valueMapper is null
   */
  public static <E extends IElement> MultiValue<E> forElementsOfSameTypeWithNameAndAdderAndValueMapper(
    final String name,
    final Consumer<E> adder,
    final Function<INode<?>, E> valueMapper) {
    return new MultiValue<>(name, adder, valueMapper, IElement::getSpecification);
  }

  /**
   * @param name
   * @param adder
   * @param valueMapper
   * @param <E>         the type of the values of a {@link MultiValue}
   * @return a new {@link MultiValue} with the given name, adder and valueMapper
   *         that can store {@link IElement}s that are of the type of <E>
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given valueMapper is null
   */
  public static <E extends IElement> MultiValue<E> forElementsWithNameAndAdderAndValueMapper(
    final String name,
    final Consumer<E> adder,
    final Function<INode<?>, E> valueMapper) {
    return new MultiValue<>(name, adder, valueMapper, e -> Node.withChildNode(e.getSpecification()));
  }

  /**
   * @param name
   * @param adder
   * @return a new {@link MultiValue} with the given name and adder and that can
   *         store {@link Integer}s.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given adder is null.
   */
  public static MultiValue<Integer> forIntsWithNameAndAdder(final String name, final Consumer<Integer> adder) {
    return new MultiValue<>(name, adder, INode::toInt, Node::withHeader);
  }

  /**
   * @param name
   * @param adder
   * @return a new {@link MultiValue} with the given name and adder and that can
   *         store {@link String}s.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given adder is null.
   */
  public static MultiValue<String> forStringsWithNameAndAdder(final String name, final Consumer<String> adder) {
    return new MultiValue<>(name, adder, INode::getHeader, Node::withHeader);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addValue(final V value) {
    values.addAtEnd(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    values.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
    for (final var v : getStoredValues()) {
      final var attribute = mapValueToSpecification(v).withNewHeader(getName());

      list.addAtEnd(attribute);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<V> getStoredValues() {
    return values;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return values.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeAllOccurrencesOfValue(final V value) {
    values.removeAllOccurrencesOf(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addOrChangeValue(final V value) {
    adder.accept(value);
  }
}
