/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.valueproperty;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.element.valueproperty.IMultiValueProperty;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link MultiValueProperty}.
 */
public final class MultiValueProperty<V> extends AbstractValueProperty<V> implements IMultiValueProperty<V> {
  private final LinkedList<V> values = LinkedList.createEmpty();

  private final Consumer<V> adder;

  /**
   * Creates a new {@link MultiValueProperty} with the given name, adder, valueMapper and
   * specificationMapper.
   * 
   * @param name
   * @param valueMapper
   * @param adder
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  private MultiValueProperty(
    final String name,
    final Consumer<V> adder,
    final Function<Node<?>, V> valueMapper,
    final Function<V, Node<?>> specificationMapper) {
    super(name, valueMapper, specificationMapper);

    Validator.assertThat(adder).thatIsNamed(LowerCaseVariableNameCatalog.ADDER).isNotNull();

    this.adder = adder;
  }

  /**
   * @param name
   * @param adder
   * @param valueMapper
   * @param <E>         the type of the values of a {@link MultiValueProperty}
   * @return a new {@link MultiValueProperty} with the given name, adder and valueMapper
   *         that can store {@link Element}s
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given valueMapper is null
   */
  public static <E extends Element> MultiValueProperty<E> forElementsOfSameTypeWithNameAndAdderAndValueMapper(
    final String name,
    final Consumer<E> adder,
    final Function<Node<?>, E> valueMapper) {
    return new MultiValueProperty<>(name, adder, valueMapper, Element::getSpecification);
  }

  /**
   * @param name
   * @param adder
   * @param valueMapper
   * @param <E>         the type of the values of a {@link MultiValueProperty}
   * @return a new {@link MultiValueProperty} with the given name, adder and valueMapper
   *         that can store {@link Element}s
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given valueMapper is null
   */
  public static <E extends Element> MultiValueProperty<E> forElementsWithNameAndAdderAndValueMapper(
    final String name,
    final Consumer<E> adder,
    final Function<Node<?>, E> valueMapper) {
    return new MultiValueProperty<>(name, adder, valueMapper, e -> ImmutableNode.withChildNode(e.getSpecification()));
  }

  /**
   * @param name
   * @param adder
   * @return a new {@link MultiValueProperty} with the given name and adder and that can
   *         store {@link Integer}s
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   */
  public static MultiValueProperty<Integer> forIntsWithNameAndAdder(final String name, final Consumer<Integer> adder) {
    return new MultiValueProperty<>(name, adder, Node::toInt, ImmutableNode::withHeader);
  }

  /**
   * @param name
   * @param adder
   * @return a new {@link MultiValueProperty} with the given name and adder and that can
   *         store {@link String}s
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   */
  public static MultiValueProperty<String> forStringsWithNameAndAdder(final String name, final Consumer<String> adder) {
    return new MultiValueProperty<>(name, adder, Node::getHeader, ImmutableNode::withHeader);
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
  public void fillUpAttributesIntoList(final ILinkedList<Node<?>> list) {
    for (final var v : getStoredValues()) {
      final var attribute = mapValueToSpecification(v).withNewHeader(getName());

      list.addAtEnd(attribute);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<V> getStoredValues() {
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
