//package declaration
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class ForwardingMutableValue<V> implements IProperty, Named {

  //attribute
  private final String name;

  //attribute
  private final Consumer<V> setter;

  //attribute
  private final Supplier<V> getter;

  //attribute
  private final Function<INode<?>, V> valueCreator;

  //attribute
  private final Function<V, INode<?>> specificationCreator;

  //constructor
  public ForwardingMutableValue(
      final String name,
      final Consumer<V> setter,
      final Supplier<V> getter,
      final Function<INode<?>, V> valueCreator,
      final Function<V, INode<?>> specificationCreator) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
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

  //static method
  public static ForwardingMutableValue<Boolean> forBoolean(
      final String name,
      final Consumer<Boolean> setter,
      final Supplier<Boolean> getter) {
    return new ForwardingMutableValue<>(name, setter, getter, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
  }

  //static method
  public static ForwardingMutableValue<Integer> forInt(
      final String name,
      final Consumer<Integer> setter,
      final Supplier<Integer> getter) {
    return new ForwardingMutableValue<>(name, setter, getter, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  //static method
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

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public boolean addedOrChangedAttribute(INode<?> attribute) {

    if (hasName(attribute.getHeader())) {
      setter.accept(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  //method
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(specificationCreator.apply(getter.get()));
  }
}
