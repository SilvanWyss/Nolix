package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.element.property.IProperty;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link ForwardingMutableValue}.
 */
public final class ForwardingMutableValue<V> implements IProperty, INameHolder {
  private final String name;

  private final Consumer<V> setter;

  private final Supplier<V> getter;

  private final Function<INode<?>, V> valueCreator;

  private final Function<V, INode<?>> specificationCreator;

  public ForwardingMutableValue(
    final String name,
    final Consumer<V> setter,
    final Supplier<V> getter,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
    Validator.assertThat(getter).thatIsNamed("getter").isNotNull();
    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

    this.name = name;
    this.setter = setter;
    this.getter = getter;
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
  }

  public static ForwardingMutableValue<Boolean> forBoolean(
    final String name,
    final Consumer<Boolean> setter,
    final Supplier<Boolean> getter) {
    return new ForwardingMutableValue<>(name, setter, getter, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
  }

  public static ForwardingMutableValue<Integer> forInt(
    final String name,
    final Consumer<Integer> setter,
    final Supplier<Integer> getter) {
    return new ForwardingMutableValue<>(name, setter, getter, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  public static ForwardingMutableValue<String> forString(
    final String name,
    final Consumer<String> setter,
    final Supplier<String> getter) {
    return new ForwardingMutableValue<>(
      name,
      setter,
      getter,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      (final String s) -> {
        if (s.isEmpty()) {
          return Node.EMPTY_NODE;
        }

        return Node.withChildNode(s);
      });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(INode<?> attribute) {
    if (hasName(attribute.getHeader())) {
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(specificationCreator.apply(getter.get()));
  }
}
