package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

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

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
    GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

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

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean addedOrChangedAttribute(INode<?> attribute) {

    if (hasName(attribute.getHeader())) {
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(specificationCreator.apply(getter.get()));
  }
}
